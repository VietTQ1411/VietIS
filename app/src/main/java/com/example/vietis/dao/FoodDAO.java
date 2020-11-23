package com.example.vietis.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.vietis.Data.entity.Food;

import java.util.List;

@Dao
public interface FoodDAO {
    @Query("SELECT * FROM Food")
    List<Food> getAll();
    @Query("DELETE FROM Food")
    void deleteAll();
}
