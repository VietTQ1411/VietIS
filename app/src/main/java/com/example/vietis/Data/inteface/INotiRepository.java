package com.example.vietis.Data.inteface;

import com.example.vietis.Data.entity.Notification;

import java.util.ArrayList;

public interface INotiRepository {
    void getNotiMessage(String msg, Exception error);
    void getNotiList(ArrayList<Notification> notifications,Exception error);
}