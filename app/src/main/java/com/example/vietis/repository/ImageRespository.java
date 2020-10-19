package com.example.vietis.repository;

import com.example.vietis.entity.Image;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class ImageRespository {
    ImageRespository instance;
    ArrayList<Image> arrayListImage;

    private ImageRespository() {
    }

    public ArrayList<Image> getArrayListImage() {
        return arrayListImage;
    }

    public ImageRespository getInstance() {
        if (instance == null) {
            instance = new ImageRespository();
        }
        return instance;
    }

    public static Image generateRandomImage() {
        return Image.builder()
                .ID(1)
                .imageURL("https://source.unsplash.com/random")
                .build();
    }
}
