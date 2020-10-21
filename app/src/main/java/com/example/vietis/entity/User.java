package com.example.vietis.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.vietis.helpers.DateConverter;
import com.example.vietis.helpers.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import okhttp3.internal.Util;

@Data
@Builder
@Getter
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "email")
    @Builder.Default
    private String email = "";
    @ColumnInfo(name = "password")
    private String password = "";
    @Builder.Default
    private String hashedPassword = "";
    @Builder.Default
    private String name = "";
    private int imageId;
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private int userType = 1;
    @Builder.Default
    private String tokenKey = "";

    @Builder.Default
    @TypeConverters(DateConverter.class)
    private Date expireDate = Calendar.getInstance().getTime();

    public static User createUserFromJSONObject(JSONObject jsonObject) {
        try {
            return User.builder()
                    .id(jsonObject.getInt("id"))
                    .email(jsonObject.getString("email"))
                    .hashedPassword(jsonObject.getString("hashPassword"))
                    .name(jsonObject.getString("name"))
                    .imageId(jsonObject.getInt("imageId"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .address(jsonObject.getString("address"))
                    .userType(jsonObject.getInt("userType"))
                    .tokenKey(jsonObject.getString("tokenKey"))
                    .expireDate(jsonObject.getString("expireDate"))

                    .build();

        } catch (JSONException e) {
            return null;
        }
    }
}
