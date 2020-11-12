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

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StoreDeatilActivityModel extends ViewModel implements ICommentRepository, IStoreDeatilRepository {
    private MutableLiveData<List<Object>> dataStore = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Comment>> listComment = new MutableLiveData<>();

    public LiveData<List<Object>> getData() {
        return dataStore;
    }

    public LiveData<ArrayList<Comment>> getCommentData() {
        return listComment;
    }

    public void getStoreDetail(String tokenkey, String id) {
        StoreRepository.getInstance(this, this).getDetailStore(tokenkey, id);
    }

    @Override
    public void getCommentLimit(final ArrayList<Comment> arrayListComment, final Exception error) {
        final StoreDeatilActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.listComment.setValue(error == null ? arrayListComment : new ArrayList<Comment>());
            }
        });
    }

    @Override

    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error) {

    }

    @Override
    public void getStoreDeatil(final List<Object> data, final Exception error) {
        final StoreDeatilActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.dataStore.setValue(error == null ? data : new ArrayList<Object>());
            }
        });
    }
}
