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
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.activities.Home.ui.home.HomeFragment;
import com.example.vietis.activities.Home.ui.store.StoreFragment;


import java.util.ArrayList;
import java.util.List;

public class ListActivityModel extends ViewModel implements IStoreRepository, IFoodRespository {

    ArrayList<Shop> list = new ArrayList<>();
    private StoreFragment storefragment;

    public ListActivityModel(StoreFragment fragment) {
        this.storefragment = fragment;
    }

    private HomeFragment homefragment;
    ArrayList<Food> listfood = new ArrayList<>();

    public ListActivityModel(HomeFragment homefragment) {
        this.homefragment = homefragment;
    }

    /**
     * @param query
     */
    public ArrayList<Shop> searchShop(String query) {
        return (ArrayList<Shop>) ShopRepository
                .getInstance(this).searchShop(getStoreData(),query);
    }

    /**
     * @param query
     */
    public ArrayList<Food> searchFood(String query) {
        return (ArrayList<Food>) FoodRespository
               .getInstance(this).searchFood(
                       getFooodData(),query);
    }


    /**
     * @param search
     * @param page
     */
    public void searchStoreFormServerWithPage(String search, int page) {
        ShopRepository.getInstance(this).getShopPaging(search, page);
    }

    /**
     * @param search
     * @param offset
     */
    public void searchFoodFormServerWithPage(String search, int offset) {
        FoodRespository.getInstance(this).getFoodPaging(search, offset);
    }



    @Override
    public void getShopData() {
        storefragment.setUpData(getStoreData());
    }

    public ArrayList<Shop> getStoreData() {
        list.clear();
        for (Object t : MutableArray.getArrayList()) {
            list.add((Shop) t);
        }
        return list;
    }


    public ArrayList<Food> getFooodData(){
        listfood.clear();
        for(Object t: MutableArray.getArrayList()){
            listfood.add((Food) t);
        }
        return  listfood;
    }
    @Override
    public void getFoodData() {
        homefragment.setUpData(getFooodData());
    }
}
