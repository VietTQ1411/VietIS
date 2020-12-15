package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.IFoodRespository;
import com.example.vietis.Data.IRepository.repository.FoodRespository;
import com.example.vietis.Data.entity.Food;

import java.util.ArrayList;

public class HomeActivityViewModel extends ViewModel implements IFoodRespository {
    private MutableLiveData<ArrayList<Food>> foods = new MutableLiveData<>();
    private ArrayList<Food> listFood = new ArrayList<>();
    public LiveData<ArrayList<Food>> getFoods(){ return foods;}


    public void getAllFoods(String query, int page){
        FoodRespository.getInstance(this).getFoodPaging(query,page);
    }

    @Override
    public void getFoodData(ArrayList<Food> foods) {
        listFood.addAll(foods);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                HomeActivityViewModel.this.foods.setValue(listFood);
            }
        });
    }
    public void destroyActivity(){
        listFood.clear();
    }
}
