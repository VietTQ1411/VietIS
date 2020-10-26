package com.example.vietis.repository;

import com.example.vietis.entity.Image;

import java.util.ArrayList;

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

    public ArrayList<Image> getImageByID(int[] idArray) {
        ArrayList<Image> fullData = Image.generateFakeImageArray();
        ArrayList<Image> fakeData = new ArrayList<Image>();
        for (int i = 0; i < idArray.length; i++) {
            for (int j = 0; j < fakeData.size(); j++) {

            }
        }
        return fakeData;
    }
}
