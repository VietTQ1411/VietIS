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

    public static ArrayList<Comment> generateFoodArray() {
        ArrayList<Comment> list = new ArrayList<>();
        list.add(Comment.builder()
                .id(1)
                .userName("Trần Việt")
                .imgUserURL("https://cdn.becungshop.vn/images/blog/tro-thanh-fan-cung-cua-be-cung-shop-nhan-ngay-ma-giam-gia-20-9a5e157d.jpg")
                .Content("đây là món ăn ngon nhất mình từng ăn ở khu vực đó đáng giá thật sự")
                .dateCreate("10-11-2020")
                .build());
        list.add(Comment.builder()
                .id(2)
                .userName("Danh Tùng")
                .imgUserURL("https://cdn.becungshop.vn/images/blog/tro-thanh-fan-cung-cua-be-cung-shop-nhan-ngay-ma-giam-gia-20-9a5e157d.jpg")
                .Content("cũng được")
                .dateCreate("12-11-2020")
                .build());
        list.add(Comment.builder()
                .id(3)
                .userName("Công sơn")
                .imgUserURL("https://cdn.becungshop.vn/images/blog/tro-thanh-fan-cung-cua-be-cung-shop-nhan-ngay-ma-giam-gia-20-9a5e157d.jpg")
                .Content("Ngon đó")
                .dateCreate("12-11-2020")
                .build());
        return list;
    }
}
