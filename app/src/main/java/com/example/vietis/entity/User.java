package com.example.vietis.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "email")
    @Builder.Default
    private String email ="";
    @ColumnInfo(name = "password")
    private String password="";
    @Builder.Default
    private String hashedPassword="";
    @Builder.Default
    private String name="";
    private int imageId;
    @Builder.Default
    private String phoneNumber="";
    @Builder.Default
    private String address="";
    @Builder.Default
    private int userType=1;
    @Builder.Default
    private String tokenKey="";
    @Builder.Default
    private Date expiredDate= new Date(System.currentTimeMillis());

    public static User createUserFromJSONObject(JSONObject jsonObject) {
        try{
            return User.builder()
                    .id(jsonObject.getInt("id"))
                    .email(jsonObject.getString("email"))
                    .hashedPassword(jsonObject.getString("hashedPassword"))
                    .name(jsonObject.getString("name"))
                    .imageId(jsonObject.getInt("imageId"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .address(jsonObject.getString("address"))
                    .userType(jsonObject.getInt("userType"))
                    .tokenKey(jsonObject.getString("tokenKey"))
                    .expiredDate(Utility.convertToDate(jsonObject.getString("expiredDate")))
                    .build();

        }catch (JSONException e){
            return null;
        }
    }
}
