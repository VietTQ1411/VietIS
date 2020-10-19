package com.example.vietis.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Image {
    private int ID;
    private String imageURL;
    private String type;
}
