package com.backend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class FoodItemScore {

    @Id
    @GeneratedValue
    private Long id;

    private int score;
    private String itemName;
    private String badIngredients;
    private LocalDateTime timestamp;

    @ManyToOne
    private WebUser user;

    private String getItemName() {
        return itemName;
    }

    private void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String getBadIngredients() {
        return badIngredients;
    }

    private void setBadIngredients(String badIngredients) {
        this.badIngredients = badIngredients;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }
}