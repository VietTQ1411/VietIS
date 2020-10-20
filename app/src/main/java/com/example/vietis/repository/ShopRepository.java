package com.example.vietis.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IShopRepository;

import java.util.ArrayList;

public class ShopRepository {

    private static ShopRepository instance;
    private IShopRepository iShopRepository;

    private ShopRepository(IShopRepository iShopRepository) {
        this.iShopRepository = iShopRepository;
    }

    public static ShopRepository getInstance(IShopRepository iShopRepository) {
        if (instance == null) {
            instance = new ShopRepository(iShopRepository);
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Shop>> getShopData() {
        MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = new MutableLiveData<>();
        mutableLiveDataShop.setValue(Shop.generateRandomShopArray());
        return mutableLiveDataShop;
    }
}
