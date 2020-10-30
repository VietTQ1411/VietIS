package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Comment;

import java.util.ArrayList;

public interface ICommentRepository {
    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error);
}
