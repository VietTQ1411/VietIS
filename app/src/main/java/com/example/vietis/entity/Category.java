package com.example.vietis.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private int ID;
    private String name;
}
