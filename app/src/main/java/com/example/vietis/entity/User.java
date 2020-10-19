package com.example.vietis.entity;

import com.example.vietis.helpers.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class User {
    private int id;
    private String email;
    private String hashedPassword;
    private String name;
    private int imageId;
    private String phoneNumber;
    private String address;
    private String userType;
    private String tokenKey;
    private Date expiredDate;

    public User(int id, String email, String hashedPassword, String name, int imageId, String phoneNumber, String address, String userType, String tokenKey, Date expiredDate) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.imageId = imageId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
        this.tokenKey = tokenKey;
        this.expiredDate = expiredDate;
    }

    public User() {}

    public static User createUserFromJSONObject(JSONObject jsonObject) {
        try{
            return new User(
                    jsonObject.getInt("id"),
                    jsonObject.getString("email"),
                    jsonObject.getString("hashedPassword"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("imageId"),
                    jsonObject.getString("phoneNumber"),
                    jsonObject.getString("address"),
                    jsonObject.getString("userType"),
                    jsonObject.getString("tokenKey"),
                    Utility.convertToDate(jsonObject.getString("expiredDate"))
            );
        }catch (JSONException e){
            return null;
        }
    }
}
