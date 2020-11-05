package com.example.vietis.UI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.R;
import com.example.vietis.UI.view_holder.CommentItemViewHolder;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentItemViewHolder> {
    private ArrayList<Comment> arrayComment;

    public CommentAdapter(ArrayList<Comment> arrayComment) {
        this.arrayComment = arrayComment;
    }

    public void setCommentArray(ArrayList<Comment> list) {
        this.arrayComment = list;
    }

    @NonNull
    @Override
    public  CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_comment_item, parent, false);
        return new CommentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {
        holder.setCommentHolder(this.arrayComment.get(position), position);
    }

    @Override
    public int getItemCount() {
        return arrayComment.size();
    }
}
