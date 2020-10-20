package com.example.vietis.inteface;

import com.example.vietis.entity.Shop;

import java.util.ArrayList;

public interface IShopRepository {
    public void getShopData(ArrayList<Shop> arrayListShop, Exception error);
}
