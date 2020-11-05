package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Shop;

import java.util.List;

public interface IStoreDeatilRepository {
    /**
     * get store detail by store id switch over from Parent intent
     * @param data - have store detail,rating 5 -> 1 and 3 top comment
     * @param error
     */
    public void getStoreDeatil(List<Object> data, Exception error);
}
