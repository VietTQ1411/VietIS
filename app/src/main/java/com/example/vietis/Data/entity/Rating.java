package com.example.vietis.Data.entity;

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
}
