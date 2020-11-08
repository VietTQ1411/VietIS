package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.IStoreRepository;
import com.example.vietis.Data.IRepository.repository.FoodRespository;
import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.IRepository.IFoodRespository;
import com.example.vietis.Data.IRepository.repository.ShopRepository;

import java.util.ArrayList;

public class ListActivityModel extends ViewModel implements IStoreRepository, IFoodRespository {
    private MutableLiveData<ArrayList<Food>> mutableLiveDataFood = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = new MutableLiveData<>(new ArrayList<>());


    public LiveData<ArrayList<Food>> getFoodData() {
        return mutableLiveDataFood;
    }

    public LiveData<ArrayList<Shop>> getShopData() {
        return mutableLiveDataShop;
    }

    /**
     * @param query
     */
    public ArrayList<Shop> searchShop(String query) {
        return (ArrayList<Shop>) ShopRepository
                .getInstance(this,mutableLiveDataShop).searchShop(query);
    }

    /**
     * @param query
     */
    public ArrayList<Food> searchFood(String query) {
        return (ArrayList<Food>) FoodRespository
                .getInstance(this,mutableLiveDataFood).searchFood(query);
    }

    /**
     */
    public void clearDataShop() {
        mutableLiveDataShop.setValue(new ArrayList<>());
    }

    /**
     *
     */
    public void clearDataFood() {
        mutableLiveDataFood.setValue(new ArrayList<>());
    }

    /**
     * @param search
     * @param page
     */
    public void searchStoreFormServerWithPage(String search,int page) {
        ShopRepository.getInstance(this,mutableLiveDataShop).getShopPaging(search,page);
    }
    /**
     * @param search
     * @param page
     */
    public void searchFoodFormServerWithPage(String search,int page) {
        FoodRespository.getInstance(this,mutableLiveDataFood).getFoodPaging(search,page);
    }
    @Override
    public void getFoodData(final ArrayList<Food> arrayListFood, final Exception error) {
        final ListActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataFood.setValue(error == null ? arrayListFood : new ArrayList<>());
            }
        });
    }

    @Override
    public void getShopData(final ArrayList<Shop> arrayListShop, final Exception error) {
        final ListActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataShop.setValue(error == null ? arrayListShop : new ArrayList<>());
            }
        });
    }
}
