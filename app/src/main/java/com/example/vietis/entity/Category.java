package com.example.vietis.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Category {
    private int ID;
    private String name;
}
