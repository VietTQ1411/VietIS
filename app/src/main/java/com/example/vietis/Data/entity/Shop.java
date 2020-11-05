package com.example.vietis.Data.entity;

import com.example.vietis.Data.IRepository.repository.Config;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Shop implements Serializable {
    @Builder.Default
    private int ID = 1;
    @Builder.Default
    private String name = "";
    @Builder.Default
    private String address = "";
    @Builder.Default
    private float rating = 0;
    @Builder.Default
    private String phoneNumber = "";
    @Builder.Default
    private String imageURL = "";
    @Builder.Default
    private String description = "";


    public boolean shopContainQuery(String query) {
        return Config.containIgnoreCase(this.toString(), query);
    }

    @Override
    public String toString() {
        return ID + "-" + name + "-" + address + "-" + rating + "-" + phoneNumber + "-" + imageURL;
    }

    public static Shop generateShopFromJSON(JSONObject jsonObject) {
        try {
            return Shop.builder()
                    .ID(jsonObject.getInt("id"))
                    .name(jsonObject.getString("name"))
                    .address(jsonObject.getString("address"))
                    .phoneNumber(jsonObject.getString("phoneNumber"))
                    .imageURL(jsonObject.getJSONObject("Image_model").getString("imageURL"))
                    .description(jsonObject.getString("description"))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Shop> generateRandomShopArray() {
        ArrayList<Shop> list = new ArrayList<>();
        list.add(Shop.builder()
                .ID(1)
                .name("Truong Giang - Cau Giay - Banh mi")
                .address("50 Duy Tan, Cau Giay, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://cdn.becungshop.vn/images/blog/tro-thanh-fan-cung-cua-be-cung-shop-nhan-ngay-ma-giam-gia-20-9a5e157d.jpg")
                .build());
        list.add(Shop.builder()
                .ID(2)
                .name("Cuom chien - Com tam - Com cam")
                .address("66 Le Duc Thang, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://cdn.tgdd.vn/2020/03/GameApp/unnamed-200x200-3.png")
                .build());
        list.add(Shop.builder()
                .ID(3)
                .name("Pho bo Hue")
                .address("88 Lang Ha, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://elevate.bike/wp-content/uploads/2019/12/service-logo-200x200.png")
                .build());
        list.add(Shop.builder()
                .ID(4)
                .name("Bun Ga Son La")
                .address("50 Duy Tan, Cau Giay, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://bluecowbarrydale.com/wp-content/uploads/2019/04/Bluecow-logo-200x200.png")
                .build());
        list.add(Shop.builder()
                .ID(5)
                .name("Bim Bim Lao Cai")
                .address("120 Tran Duy Hung")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://cdn.shopify.com/s/files/1/0055/7105/1602/files/hdlogotransparent_180x.jpg?v=1575486343")
                .build());
        list.add(Shop.builder()
                .ID(6)
                .name("Thit Cho Ba Dinh")
                .address("150 Thai Ha, Ha Noi")
                .rating(3.5f)
                .phoneNumber("0357467491")
                .imageURL("https://www.ami.co.nz/images/Shop.png")
                .build());
        return list;
    }
}
