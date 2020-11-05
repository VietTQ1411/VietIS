package com.example.vietis.Data.view_model;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.entity.Comment;

import java.util.ArrayList;

public class CommentActivityModel extends ViewModel implements ICommentRepository {
    private MutableLiveData<ArrayList<Comment>> mutableLiveDataComment = null;

    public LiveData<ArrayList<Comment>> getCommentData() {

        return mutableLiveDataComment;
    }


    public void init() {
        if (mutableLiveDataComment == null) {
            this.mutableLiveDataComment = new MutableLiveData<>();
        }
    }

    @Override
    public void getCommentLimit(ArrayList<Comment> arrayListComment, Exception error) {

    }

    @Override
    public void getCommentData(final ArrayList<Comment> arrayListComment, final Exception error) {
        final CommentActivityModel that = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                that.mutableLiveDataComment.setValue(error == null ? arrayListComment : new ArrayList<Comment>());
            }
        });
    }

}