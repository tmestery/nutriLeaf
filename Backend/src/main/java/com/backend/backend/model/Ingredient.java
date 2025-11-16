package com.backend.backend.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_seq_gen")
    @SequenceGenerator(name = "ingredient_seq_gen", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Long id;

    private String name;
    private double quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}