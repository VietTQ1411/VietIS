package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public interface ICommentRepository {


    /**
     * get limit of list comment for store or food to show in detail
     * remain : 3 elements
     *
     * @param arrayListComment

     */
    public void getCommentLimit(List<Comment> arrayListComment);


    /**
     * get max 10 elements for each time call function show all of comment in comment dialog
     * remain: 10 elements each time
     *
     * @param arrayListComment
     * @param error
     */
    public void getCommentData(ArrayList<Comment> arrayListComment, Exception error);
}
