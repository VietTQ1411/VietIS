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
import com.example.vietis.activities.Home.ui.store.StoreFragment;

import java.util.ArrayList;

public class ListActivityModel extends ViewModel implements IStoreRepository, IFoodRespository {

    ArrayList<Shop> list = new ArrayList<>();
    private StoreFragment fragment;

    public ListActivityModel(StoreFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * @param query
     */
    public ArrayList<Shop> searchShop(String query) {
//        return (ArrayList<Shop>) ShopRepository
//                .getInstance(this,mutableLiveDataShop).searchShop(query);
        return null;
    }

    /**
     * @param query
     */
    public ArrayList<Food> searchFood(String query) {
        //return (ArrayList<Food>) FoodRespository
        //       .getInstance(this,mutableLiveDataFood).searchFood(query);
        return null;
    }

    /**
     *
     */
    public void clearDataShop() {
        //mutableLiveDataShop.setValue(new ArrayList<>());
    }

    /**
     *
     */
    public void clearDataFood() {
        // mutableLiveDataFood.setValue(new ArrayList<>());
    }

    /**
     * @param search
     * @param page
     */
    public void searchStoreFormServerWithPage(String search, int page) {
        //ShopRepository.getInstance(this,mutableLiveDataShop).getShopPaging(search,page);
    }

    /**
     * @param search
     * @param page
     */
    public void searchFoodFormServerWithPage(String search, int page) {
        // FoodRespository.getInstance(this,mutableLiveDataFood).getFoodPaging(search,page);
    }

    @Override
    public void getFoodData(final ArrayList<Food> arrayListFood, final Exception error) {

    }

    @Override
    public void getShopData(final ArrayList<Shop> arrayListShop, final Exception error) {
        fragment.setUpData(getData());
    }

    public ArrayList<Shop> getData() {
        list.clear();
        for (Object t : MutableArray.getArrayList()) {
            list.add((Shop) t);
        }
        return list;
    }
}
