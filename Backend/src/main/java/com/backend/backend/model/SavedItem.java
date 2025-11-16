package com.backend.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SavedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saved_item_seq_gen")
    @SequenceGenerator(name = "saved_item_seq_gen", sequenceName = "saved_item_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser user;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    public FoodItem foodItem;

    public String dateSaved;

    public String getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(String dateSaved) {
        this.dateSaved = dateSaved;
    }
}