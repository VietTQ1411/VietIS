package com.example.vietis.Data.view_model;

import androidx.lifecycle.ViewModel;
import com.example.vietis.Data.IRepository.IFoodRespository;
import com.example.vietis.Data.IRepository.repository.FoodRespository;
import com.example.vietis.Data.entity.Food;
import com.example.vietis.activities.Home.ui.home.FoodDetailActivity;
import com.example.vietis.activities.Home.ui.order.OrderActivity;


public class FoodDetailViewModel extends ViewModel implements IFoodRespository {

    private FoodDetailActivity foodDetailActivity;

    public FoodDetailViewModel(FoodDetailActivity fragment) {
        this.foodDetailActivity = fragment;
    }

    private OrderActivity orderActivity;

    public FoodDetailViewModel(OrderActivity fragment) {
        this.orderActivity = fragment;
    }

    @Override
    public void getFoodData() {
        foodDetailActivity.setupData((Food) MutableArray.getArrayList().get(0));
    }

    public void getFoodDetail(String id) {
        FoodRespository.getInstance(this).getFoodDetail(id);
    }
}
