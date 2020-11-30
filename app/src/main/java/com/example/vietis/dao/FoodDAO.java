package com.example.vietis.dao;

import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import androidx.room.Query;

import com.example.vietis.Data.entity.Food;

import java.util.ArrayList;

import java.util.List;

@Dao
public interface FoodDAO {
    @Query("SELECT * FROM Food WHERE id = :id")
    Food getFoodById(int id);
    @Query("SELECT * FROM Food")
    List<Food> getAllFood();
    @Query("DELETE FROM Food")
    void deleteAllFood();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFood(Food food);

}
