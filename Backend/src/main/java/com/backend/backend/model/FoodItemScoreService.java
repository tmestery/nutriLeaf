package com.backend.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemScoreService {

    @Autowired
    private FoodItemScoreRepository foodItemScoreRepo;

    public double getWebUserAverageScore(String username) {
        return foodItemScoreRepo.getWebUserAverageScore(username);
    }

    public long getUserTotalWarnings(String username) {
        return foodItemScoreRepo.getTotalWarnings(username);
    }

    public int getTotalWarningsForItem(String itemName) {
        List<FoodItemScore> scores = foodItemScoreRepo.findByItemName(itemName);
        return scores.stream()
                .mapToInt(FoodItemScore::getWarningCount)
                .sum();
    }
}