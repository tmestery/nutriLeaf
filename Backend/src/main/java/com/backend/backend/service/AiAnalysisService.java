package com.backend.backend.service;

import com.backend.backend.model.FoodItem;
import com.backend.backend.model.FoodItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AiAnalysisService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String OLLAMA_URL = "http://localhost:11434/api/generate";

    private final FoodItemRepository foodItemRepository;

    public AiAnalysisService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public Map<String, Object> analyzeIngredientsWithRecommendation(String productName, String ingredientsList) {
        // Building the prompt
        String prompt = "You are a nutrition analysis model. You are given a product name and its ingredients list. " +
                "Your job is to evaluate the product's health quality based on: whole vs processed ingredients, added sugars, unhealthy oils, artificial additives, and fiber content.\n\n" +
                "Scoring Guide:\n" +
                "- 90-100 = Very healthy\n" +
                "- 70-89 = Mostly healthy\n" +
                "- 50-69 = Moderate\n" +
                "- 30-49 = Processed\n" +
                "- 0-29 = Highly processed\n\n" +
                "You MUST output ONLY the following JSON structure, with no extra commentary or text:\n" +
                "{\n" +
                "  \"product_name\": \"" + productName + "\",\n" +
                "  \"score\": <number>,\n" +
                "  \"analysis\": \"<short explanation>\",\n" +
                "  \"ingredients_of_concern\": [\"ingredient1\", \"ingredient2\"],\n" +
                "  \"recommended_alternative\": {\n" +
                "     \"name\": \"<similar healthier product>\",\n" +
                "     \"brand\": \"<brand name>\",\n" +
                "     \"reason\": \"<why it is healthier>\"\n" +
                "  }\n" +
                "}\n\n" +
                "Ingredients List: " + ingredientsList;

        Map<String, Object> requestBody = Map.of(
                "model", "llama3",
                "stream", false,
                "prompt", prompt
        );

        String responseString = restTemplate.postForObject(OLLAMA_URL, requestBody, String.class);

        try {
            // Parse Ollama JSON that's received
            Map<String, Object> aiResult = objectMapper.readValue(responseString, Map.class);

            // Save/update FoodItem in PSQL db
            FoodItem foodItem = foodItemRepository.findByItemName(productName)
                    .orElse(new FoodItem());

            foodItem.setItemName(productName);
            foodItem.setRawIngredientList(ingredientsList);
            foodItem.setHealthScore((int) aiResult.getOrDefault("score", 0));
            foodItem.setNutritionBreakdown((String) aiResult.getOrDefault("analysis", "N/A"));
            foodItem.setGeneratedWarnings(String.join(", ",
                    ((java.util.List<String>) aiResult.getOrDefault("ingredients_of_concern", java.util.List.of()))));

            // Recommended the alternative object
            Map<String, Object> alt = (Map<String, Object>) aiResult.get("recommended_alternative");
            if (alt != null) {
                String recText = String.format("Try %s by %s — %s",
                        alt.getOrDefault("name", "another product"),
                        alt.getOrDefault("brand", "unknown brand"),
                        alt.getOrDefault("reason", ""));
                foodItem.setRecommendations(recText);
            }

            foodItemRepository.save(foodItem);

            return aiResult;

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of(
                    "product_name", productName,
                    "score", 0,
                    "analysis", "Error parsing LLM response",
                    "ingredients_of_concern", new String[]{},
                    "recommended_alternative", Map.of()
            );
        }
    }
}
