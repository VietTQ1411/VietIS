package com.example.vietis.Data.inteface;

import com.example.vietis.Data.entity.Shop;

import java.util.ArrayList;

public interface IShopRepository {
    public void getShopData(ArrayList<Shop> arrayListShop, Exception error);
}
