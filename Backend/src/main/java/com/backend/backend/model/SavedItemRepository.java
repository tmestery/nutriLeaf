package com.backend.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavedItemRepository extends JpaRepository<SavedItem, Long> {

    List<SavedItem> findAllByUser(WebUser user);
    SavedItem findByUserAndFoodItem(WebUser user, FoodItem foodItem);
    void deleteByUserAndFoodItem(WebUser user, FoodItem foodItem);
}