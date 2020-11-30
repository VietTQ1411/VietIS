package com.example.vietis.Data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vietis.Data.IRepository.repository.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
@Entity
public class Shop implements Serializable {
    @PrimaryKey
    @Builder.Default
    private int ID = 0;
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private float rating = 0;
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private String imageURL = "";
    @Builder.Default
    private String description = "";


    public boolean shopContainQuery(String query) {
        return Config.containIgnoreCase(this.toString(), query);
    }

    @Override
    public String toString() {
        return name + "-" + address + "-" + rating + "-" + phoneNumber + "-" + imageURL;
    }

    public Shop(int ID, String name, String address, float rating, String phoneNumber, String imageURL, String description) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.description = description;
    }

    public static Shop generateShopFromJSON(JSONObject jsonObject) {
        try {
            return Shop.builder()
                    .ID(jsonObject.getInt("id"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getString("address"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public static Shop generateShopFromJSON(JSONArray jsonArray) {

        try {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return Shop.builder()
                    .ID(jsonObject.getInt("id"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getString("address"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
