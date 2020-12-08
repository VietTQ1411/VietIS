package com.example.vietis.UI.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.IRepository.repository.Config;
import com.example.vietis.Data.entity.Food;
import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.Data.inteface.IView;
import com.example.vietis.R;
import com.squareup.picasso.Picasso;

public class FoodItemViewHolder extends RecyclerView.ViewHolder implements IView {

    //UI holders
    private LinearLayout linearFoodItem;
    private ImageView imageViewFoodIcon;
    private TextView txtFoodName;
    private TextView txtPrice;
    //Parent
    private IListView parent;

    public FoodItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mappingUI();
        setupUI();
    }

    public void setParent(IListView parent) {
        this.parent = parent;
    }

    public void setFoodItem(final Food food, int pos) {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parent != null) {
                    parent.navigateToFoodDetail(food.getID());
                }
            }
        };
        Config.setChildViewOnClickListener(linearFoodItem, listener);

        Picasso.get().load(food.getImageURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(100, 100)
                .centerCrop()
                .into(imageViewFoodIcon);
        txtFoodName.setText(food.getName());
        txtPrice.setText(food.getPrice() + "");
    }

    @Override
    public void mappingUI() {
        linearFoodItem = itemView.findViewById(R.id.linearFoodItem);
        imageViewFoodIcon = itemView.findViewById(R.id.imageViewFoodIcon);
        txtFoodName = itemView.findViewById(R.id.txtFoodName);
        txtPrice = itemView.findViewById(R.id.txtPrice);
    }

    @Override
    public void setupUI() {
    }

}
