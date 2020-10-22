package com.example.vietis.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.entity.Food;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IFoodRespository;

import java.util.ArrayList;

public class FoodRespository {
    private static FoodRespository instance;
    private IFoodRespository iFoodRespository;

    private FoodRespository(IFoodRespository iFoodRespository) {
        this.iFoodRespository = iFoodRespository;
    }

    public static FoodRespository getInstance(IFoodRespository iFoodRespository) {
        if (instance == null) {
            instance = new FoodRespository(iFoodRespository);
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Food>> searchFoodFromFakeData(String query) {
        MutableLiveData<ArrayList<Food>> mutableLiveDataFood = new MutableLiveData<>();
        ArrayList<Food> data = Food.generateFoodArray();
        for (Food item : data) {
            if (!item.getName().contains(query)) {
                data.remove(item);
            }
        }
        mutableLiveDataFood.setValue(data);
        return mutableLiveDataFood;
    }
}
