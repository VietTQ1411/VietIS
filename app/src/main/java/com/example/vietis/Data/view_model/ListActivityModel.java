package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.inteface.IFoodRespository;
import com.example.vietis.Data.inteface.IStoreRepository;
import com.example.vietis.Data.inteface.repository.ShopRepository;

import java.util.ArrayList;

public class ListActivityModel extends ViewModel implements IStoreRepository, IFoodRespository {
    private MutableLiveData<ArrayList<Food>> mutableLiveDataFood = null;
    private MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = null;

    public LiveData<ArrayList<Food>> getFoodData() {
        return mutableLiveDataFood;
    }

    public LiveData<ArrayList<Shop>> getShopData() {
        return mutableLiveDataShop;
    }

    public void init(boolean isEmptyShopList, boolean isEmptyFoodList) {
        if (mutableLiveDataFood == null) {
            this.mutableLiveDataFood = new MutableLiveData<>();
        }
        if (mutableLiveDataShop == null) {
            this.mutableLiveDataShop = new MutableLiveData<>(isEmptyShopList ? new ArrayList<Shop>() :
                    ShopRepository.getInstance(this).gennerateFakeShopArray());
        }
    }

    public void searchShopFromFakeData(String query) {
        mutableLiveDataShop.setValue(ShopRepository.getInstance(this).searchShopInFakeData(query));
    }

    public void searchFoodFromFakeData() {

    }

    @Override
    public void getFoodData(final ArrayList<Food> arrayListFood, final Exception error) {
        final ListActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataFood.setValue(error == null ? arrayListFood : new ArrayList<Food>());
            }
        });
    }

    @Override
    public void getShopData(final ArrayList<Shop> arrayListShop, final Exception error) {
        final ListActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataShop.setValue(error == null ? arrayListShop : new ArrayList<Shop>());
            }
        });
    }
}
