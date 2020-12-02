package com.example.vietis.Data.view_model;

import android.os.Looper;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.IRepository.IStoreDetailRepository;
import com.example.vietis.Data.IRepository.repository.StoreRepository;
import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.activities.Home.ui.store.StoreDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class StoreDetailActivityModel extends ViewModel implements ICommentRepository, IStoreDetailRepository {
    private MutableLiveData<Shop> store = new MutableLiveData<>();
    private MutableLiveData<List<Comment>> comments = new MutableLiveData<>();
    public LiveData<Shop> getShop(){
        return store;
    }
    public  LiveData<List<Comment>> getComments(){
        return comments;
    }
    public StoreDetailActivityModel() {}

    public void getStoreDetail(String tokenkey, String id) {
        StoreRepository.getInstance(this, this).getDetailStore(tokenkey, id);
    }

    @Override
    public void getCommentLimit(final List<Comment> arrayListComment) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                StoreDetailActivityModel.this.comments.setValue(arrayListComment);
            }
        });
    }

    @Override

    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error) {

    }

    @Override
    public void getStoreDetail(Shop shop, Exception error) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                StoreDetailActivityModel.this.store.setValue((error==null ? shop : Shop.builder().build()));
            }
        });
    }
}
