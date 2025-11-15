package com.backend.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ProductAlternative {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne FoodItem originalItemId;
    @ManyToOne FoodItem alternativeItemId;
    private String improvementReason;

    public String getImprovementReason() {
        return improvementReason;
    }

    public void setImprovementReason(String improvementReason) {
        this.improvementReason = improvementReason;
    }
}