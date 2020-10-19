package com.example.vietis.entity;


import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Food {
    private int ID;
    private int shopID;
    private String name;
    private int cateID;
    private float price;
    private String description;
    private int imageID;

    public static ArrayList<Food> generateFoodArray() {
        ArrayList<Food> list = new ArrayList<>();
        list.add(Food.builder()
                .ID(1)
                .shopID(1)
                .name("Bun cha")
                .cateID(1)
                .price(30000)
                .description("this is a food")
                .imageID(1)
                .build());
        list.add(Food.builder()
                .ID(2)
                .shopID(1)
                .name("Bun ca")
                .cateID(1)
                .price(35000)
                .description("this is a food")
                .imageID(2)
                .build());
        list.add(Food.builder()
                .ID(3)
                .shopID(2)
                .name("Pho bo")
                .cateID(2)
                .price(30000)
                .description("this is a food")
                .imageID(3)
                .build());
        list.add(Food.builder()
                .ID(4)
                .shopID(2)
                .name("Pho ga")
                .cateID(1)
                .price(25000)
                .description("this is a food")
                .imageID(4)
                .build());
        list.add(Food.builder()
                .ID(5)
                .shopID(3)
                .name("Banh mi bon bon")
                .cateID(3)
                .price(20000)
                .description("this is a food")
                .imageID(5)
                .build());
        list.add(Food.builder()
                .ID(6)
                .shopID(3)
                .name("Banh mi que")
                .cateID(3)
                .price(15000)
                .description("this is a food")
                .imageID(6)
                .build());
        return list;
    }
}
