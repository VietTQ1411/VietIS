package com.example.vietis.Data.view_model;

import java.util.ArrayList;

public class MutableArray {
    private static ArrayList<Object> listData = null;

    public static ArrayList<Object> getArrayList() {
        if (listData == null) {
            listData = new ArrayList<>();
        }
        return listData;
    }
    public static void clearData(){
        if (listData == null) {
            listData = new ArrayList<>();
        }
        else
        listData.clear();
    }
}
