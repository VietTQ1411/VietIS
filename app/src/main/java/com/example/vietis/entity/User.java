package com.example.vietis.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class User {
    private int id;
    private String email;
    private String hashedPassword;
    private String name;
    private String imageId;
    private String phoneNumber;
    private String address;
    private String userType;
    private String tokenKey;
    private Date expiredDate;
}
