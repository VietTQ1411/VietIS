package com.example.vietis.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vietis.entity.User;

import java.util.List;
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();
    @Query("SELECT * FROM User WHERE email = :email")
    User getLoginUser(String email);
    @Insert()
    void insertUser(User user);
}
