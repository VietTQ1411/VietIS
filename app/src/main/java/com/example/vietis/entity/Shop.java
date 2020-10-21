package com.example.vietis.entity;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Shop {
    @Builder.Default
    private int ID = 1;
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private float rating = 0;
    @Builder.Default
    private int voucherID = 1;
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private int imageID = 1;

    public static ArrayList<Shop> generateRandomShopArray() {
        ArrayList<Shop> list = new ArrayList<>();
        list.add(Shop.builder()
                .ID(1)
                .name("Truong Giang - Cau Giay - Banh mi")
                .address("50 Duy Tan, Cau Giay, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(1)
                .build());
        list.add(Shop.builder()
                .ID(2)
                .name("Cuom chien - Com tam - Com cam")
                .address("66 Le Duc Thang, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(2)
                .build());
        list.add(Shop.builder()
                .ID(3)
                .name("Pho bo Hue")
                .address("88 Lang Ha, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(3)
                .build());
        list.add(Shop.builder()
                .ID(4)
                .name("Bun Ga Son La")
                .address("50 Duy Tan, Cau Giay, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(4)
                .build());
        list.add(Shop.builder()
                .ID(5)
                .name("Bim Bim Lao Cai")
                .address("120 Tran Duy Hung")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(5)
                .build());
        list.add(Shop.builder()
                .ID(6)
                .name("Thit Cho Ba Dinh")
                .address("150 Thai Ha, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageID(6)
                .build());
        return list;
    }
}
