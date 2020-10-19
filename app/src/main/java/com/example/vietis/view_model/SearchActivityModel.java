package com.example.vietis.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.entity.Food;
import com.example.vietis.inteface.IFoodRespository;
import com.example.vietis.respository.FoodRespository;

import java.util.ArrayList;

public class SearchActivityModel extends ViewModel implements IFoodRespository {
    private MutableLiveData<ArrayList<Food>> mutableLiveDataFood;

    public LiveData<ArrayList<Food>> getFoodData() {
        return mutableLiveDataFood;
    }

    public void init() {
        if (mutableLiveDataFood != null) return;
        this.mutableLiveDataFood = FoodRespository.getInstance(this).getFoodData();
    }

    @Override
    public void getFoodData(final ArrayList<Food> arrayListFood, final Exception error) {
        final SearchActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataFood.setValue(error == null ? arrayListFood : new ArrayList<Food>());
            }
        });
    }
}
