package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Shop;

import java.util.List;

public interface IStoreDetailRepository {
    /**
     * get store detail by store id switch over from Parent intent
     *
     */
   void getStoreDetail(Shop shop,Exception error);
}
