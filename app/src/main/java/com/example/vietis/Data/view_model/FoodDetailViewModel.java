package com.example.vietis.Data.view_model;

import androidx.lifecycle.ViewModel;

import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.entity.Comment;

import java.util.ArrayList;

public class FoodDetailViewModel extends ViewModel implements ICommentRepository {


    @Override
    public void getCommentLimit(ArrayList<Comment> arrayListComment, Exception error) {

    }

    @Override
    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error) {

    }
}
