package com.example.vietis.UI.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.R;
import com.example.vietis.Data.inteface.IView;
import com.squareup.picasso.Picasso;

public class CommentItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private ImageView imgUser;
    private TextView txtUserName;
    private TextView txtContentComment;
    private TextView txtDateCreate;

    public CommentItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setCommentHolder(Comment comment, int pos) {
        Picasso.get().load(comment.getImgUserURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(80, 80)
                .centerCrop()
                .into(imgUser);
        txtUserName.setText(comment.getUserName());
        txtContentComment.setText(comment.getContent());
        txtDateCreate.setText(comment.getDateCreate());
    }

    @Override
    public void mappingUI() {
        imgUser = itemView.findViewById(R.id.imgUser);
        txtUserName = itemView.findViewById(R.id.txtUserName);
        txtContentComment = itemView.findViewById(R.id.txtContentComment);
        txtDateCreate = itemView.findViewById(R.id.txtDateCreate);
    }

    @Override
    public void setupUI() {

    }
}
