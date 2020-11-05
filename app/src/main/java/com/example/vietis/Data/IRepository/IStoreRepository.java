package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Shop;

import java.util.ArrayList;

public interface IStoreRepository {
    public void getShopData(ArrayList<Shop> arrayListShop, Exception error);
}
