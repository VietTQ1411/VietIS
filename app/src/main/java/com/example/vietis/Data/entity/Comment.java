package com.example.vietis.Data.entity;

import org.json.JSONObject;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Comment {
    /**
     *
     */
    private int id;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private String imgUserURL;
    /**
     *
     */
    private String Content;
    /**
     *
     */
    private String dateCreate;


    public static Comment generateCommentFroJSon(JSONObject jsonObject) {
        try {
            return Comment.builder()
                    .id(Integer.parseInt(jsonObject.getString("id")))
                    .userName(jsonObject.getString("userName"))
                    .imgUserURL(jsonObject.getString("imgUserURL"))
                    .Content(jsonObject.getString("Content"))
                    .dateCreate(jsonObject.getString("dateCreate"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }


}
