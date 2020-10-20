package com.example.vietis.database;

import androidx.room.RoomDatabase;

import com.example.vietis.dao.UserDAO;
import com.example.vietis.entity.User;

@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();
}
