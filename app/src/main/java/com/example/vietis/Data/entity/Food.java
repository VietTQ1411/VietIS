package com.example.vietis.Data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@Entity
public class Food {
    @PrimaryKey
    @Builder.Default
    private int ID = 0;

    @Builder.Default
    private int shopID = 0;

    @Builder.Default
    private String name = "default name";

    @Builder.Default
    private String address = "unknown";

    @Builder.Default
    private String category = "unknown";

    @Builder.Default
    private float price = 0;

    @Builder.Default
    private String imageURL = "unknown";

    @Builder.Default
    private String description = "default description";

    @Builder.Default
    private int imageID = 0;


    public Food(int ID, int shopID, String name, String address, String category, float price, String imageURL, String description, int imageID) {
        this.ID = ID;
        this.shopID = shopID;
        this.name = name;
        this.address = address;
        this.category = category;
        this.price = price;
        this.imageURL = imageURL;
        this.description = description;
        this.imageID = imageID;
    }

    public static Food generateFoodFromJSON(JSONObject jsonObject) {
        try {
            return Food.builder()
                    .ID(jsonObject.getInt("id"))
                    .imageID(jsonObject.getInt("imageId"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getJSONObject("Store_model").getString("address"))
                    .category(jsonObject.getJSONObject("Category_model").getString("name"))
                    .price(jsonObject.getInt("price"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
