package com.example.vietis.Data.entity;

import org.json.JSONObject;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rating {
    /**
     *
     */
    private int id;
    /**
     *
     */
    private int starNumber;
    /**
     *
     */
    private int voteCount;

    /**
     * Rating(id,starNumber) -> rating of user
     * Rating(starNumber,voteCount) -> rating for food or store
     */


    public static Rating generateRatingCount(int starNumber,JSONObject jsonObject) {
        try {
            return Rating.builder()
                    .starNumber(starNumber)
                    .voteCount(Integer.parseInt(jsonObject.getString("count")))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
