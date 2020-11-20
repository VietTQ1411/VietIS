package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.IRepository.IStoreDeatilRepository;
import com.example.vietis.Data.IRepository.repository.StoreRepository;
import com.example.vietis.Data.IRepository.repository.UserRepository;
import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.activities.Home.ui.store.StoreDetailActivity;
import com.example.vietis.activities.Home.ui.store.StoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StoreDetailActivityModel extends ViewModel implements ICommentRepository, IStoreDeatilRepository {
    private StoreDetailActivity storefragment;

    public StoreDetailActivityModel(StoreDetailActivity fragment) {
        this.storefragment = fragment;
    }


    public void getStoreDetail(String tokenkey, String id) {
        StoreRepository.getInstance(this, this).getDetailStore(tokenkey, id);
    }

    @Override
    public void getCommentLimit(final ArrayList<Comment> arrayListComment) {
        storefragment.setUpStoreComment(arrayListComment);
    }

    @Override

    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error) {

    }

    @Override
    public void getStoreDetail() {
        storefragment.setUpStoreDetail(MutableArray.getArrayList());
    }
}
