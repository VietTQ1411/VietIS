package com.example.vietis.Data.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Notification implements Serializable {
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
                    .content(jsonObject.getString("content"))
                    .storeId(jsonObject.getInt("storeId"))
                    .foodId((jsonObject.getInt("foodId")))
                    .idType(jsonObject.getString("idType"))
                    .build();

        }catch (JSONException jsonException){
            return null;
        }
    }
    public static final ArrayList<Notification> fakeNoti(){
        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(
                Notification.builder().
                title("1")
                .imageUrl("abc")
                .content("new")
                .storeId(1)
                .foodId(2)
                .idType("food")
                .build()
                );
        notifications.add(
                Notification.builder().
                        title("2")
                        .imageUrl("abc")
                        .content("new")
                        .storeId(1)
                        .foodId(2)
                        .idType("food")
                        .build()
        );
        notifications.add(
                Notification.builder().
                        title("3")
                        .imageUrl("abc")
                        .content("new")
                        .storeId(1)
                        .foodId(2)
                        .idType("food")
                        .build()
        );
        return notifications;
    }
}
