package com.example.vietis.repository;

import android.util.Log;

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

    public void searchShopFromFakeData(String query, MutableLiveData<ArrayList<Shop>> data) {
        if (query.isEmpty()) {
            data.setValue(new ArrayList<Shop>());
            return;
        }
        Log.i("Query",query);
        ArrayList<Shop> fakeData = Shop.generateRandomShopArray();
        for (int i = 0; i < fakeData.size(); i++) {
            Shop item = fakeData.get(i);
            Log.i("Query",""+item.shopContainQuery(query));
            if (!item.shopContainQuery(query)) {
                fakeData.remove(i);
                i--;
            }
        }
        data.setValue(fakeData);
    }
}
