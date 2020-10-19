package com.example.vietis.inteface;

import com.example.vietis.entity.Food;

import java.util.ArrayList;

public interface IFoodRespository {
    public void getFoodData(ArrayList<Food> arrayListFood, Exception error);
}
