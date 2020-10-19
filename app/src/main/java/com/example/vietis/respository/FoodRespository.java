package com.example.vietis.respository;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.entity.Food;
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

    public MutableLiveData<ArrayList<Food>> getFoodData() {
        MutableLiveData<ArrayList<Food>> mutableLiveDataFood = new MutableLiveData<>();
        mutableLiveDataFood.setValue(Food.generateFoodArray());
        return mutableLiveDataFood;
    }
}
