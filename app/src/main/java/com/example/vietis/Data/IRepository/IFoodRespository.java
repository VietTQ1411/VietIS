package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.dao.FoodDAO;

import java.util.ArrayList;
import java.util.List;

public interface IFoodRespository {
    public void getFoodData();
    void getAllFood(ArrayList<Food> foodList);
}
