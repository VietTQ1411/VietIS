package com.example.vietis.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.entity.Food;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IFoodRespository;
import com.example.vietis.inteface.IShopRepository;
import com.example.vietis.repository.ShopRepository;

import java.util.ArrayList;

public class SearchActivityModel extends ViewModel implements IShopRepository, IFoodRespository {
    private MutableLiveData<ArrayList<Food>> mutableLiveDataFood = null;
    private MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = null;

    public LiveData<ArrayList<Food>> getFoodData() {
        return mutableLiveDataFood;
    }

    public LiveData<ArrayList<Shop>> getShopData() {
        return mutableLiveDataShop;
    }

    public void init() {
        if (mutableLiveDataFood == null)
            this.mutableLiveDataFood = new MutableLiveData<>();
        if (mutableLiveDataShop == null)
            this.mutableLiveDataShop = new MutableLiveData<>();
    }

    public void searchShopFromFakeData (String query){
        mutableLiveDataShop.setValue(ShopRepository.getInstance(this).searchShopInFakeData(query));
    }
    public void searchFoodFromFakeData(){

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

    @Override
    public void getShopData(final ArrayList<Shop> arrayListShop, final Exception error) {
        final SearchActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataShop.setValue(error == null ? arrayListShop : new ArrayList<Shop>());
            }
        });
    }
}
