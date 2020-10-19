package com.example.vietis.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Food {
    private int ID;
    private int shopID;
    private String name;
    private int cateID;
    private float price;
    private String description;
    private int imageID;
}
