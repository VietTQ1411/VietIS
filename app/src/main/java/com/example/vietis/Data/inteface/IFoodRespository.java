package com.example.vietis.Data.inteface;

import com.example.vietis.Data.entity.Food;

import java.util.ArrayList;

public interface IFoodRespository {
    public void getFoodData(ArrayList<Food> arrayListFood, Exception error);
}
