package com.example.vietis.Data.entity;

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

}
