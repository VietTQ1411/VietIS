package com.example.vietis.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.vietis.Data.entity.Shop;

import java.util.List;

@Dao
public interface ShopDAO {
    @Query("SELECT * FROM Shop")
    List<Shop> getAllShop();
    @Query("SELECT * FROM Shop WHERE id= :id")
    Shop getShopById(int id);
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insertShop(Shop shop);
}
