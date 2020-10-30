package com.example.vietis.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vietis.Data.entity.User;

import java.util.List;
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE email = :email AND password= :password")
    User getLoginUser(String email, String password);

    @Insert
    void insertUser(User... users);

}
