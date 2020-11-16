package com.example.vietis.Data.view_model;

import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.IOrderRepository;
import com.example.vietis.Data.IRepository.repository.OrderRepository;
import com.example.vietis.Data.entity.Order;
import com.example.vietis.activities.Home.ui.order.OrderActivity;
import com.example.vietis.activities.Home.ui.order.OrderFragment;

import java.util.ArrayList;

public class OrderActivityModel extends ViewModel implements IOrderRepository {
    private static OrderFragment orderActivity;
    private static OrderActivity orderActivity2;
    ArrayList<Order> listfood = new ArrayList<>();

    public OrderActivityModel(OrderFragment loginActivity) {
        this.orderActivity = loginActivity;
    }
    public OrderActivityModel(OrderActivity loginActivity) {
        this.orderActivity2 = loginActivity;
    }
    public void getAll() {
        OrderRepository.getInstance(this).getAll();
    }

    @Override
    public void getData() {
        orderActivity.setUpData(getFooodData());
    }

    public void addData(Order order) {
        OrderRepository.getInstance(this).addData(order);
    }
    @Override
    public void addDataDone() {
        orderActivity2.Out();
    }
    public ArrayList<Order> getFooodData() {
        listfood.clear();
        for (Object t : MutableArray.getArrayList()) {
            listfood.add((Order) t);
        }
        return listfood;
    }


}