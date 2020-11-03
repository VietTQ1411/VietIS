package com.example.vietis.Data.entity;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Notification {
    @Builder.Default
    private String title="";
    @Builder.Default
    private String imageUrl="";
    @Builder.Default
    private String content="";
    @Builder.Default
    private int storeId=0;
    @Builder.Default
    private int foodId=0;
    @Builder.Default
    private String idType="";

    public static Notification createNotificationFromJSONObject(JSONObject jsonObject){
        try{
            return Notification.builder()
                    .title(jsonObject.getString("title"))
                    .imageUrl(jsonObject.getString("imageUrl"))
                    .content(jsonObject.getString("content"))
                    .storeId(jsonObject.getInt("storeId"))
                    .foodId(jsonObject.getInt("foodId"))
                    .idType(jsonObject.getString("idType"))
                    .build();

        }catch (JSONException jsonException){
            return null;
        }
    }
}
