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

    public Food getRandomFood(){
        return Food.builder()
                .ID(1)
                .shopID(1)
                .name("Bun cha")
                .cateID(1)
                .build();
    }
}
