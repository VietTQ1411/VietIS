package com.example.vietis.Data.view_model;

import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.IRepository.IStoreDetailRepository;
import com.example.vietis.Data.IRepository.repository.StoreRepository;
import com.example.vietis.Data.entity.Comment;
import com.example.vietis.activities.Home.ui.store.StoreDetailActivity;

import java.util.ArrayList;

/**
 *
 */
public class StoreDetailActivityModel extends ViewModel implements ICommentRepository, IStoreDetailRepository {
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
