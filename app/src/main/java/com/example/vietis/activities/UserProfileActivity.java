package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.vietis.R;
import com.example.vietis.adapter.ShopAdapter;
import com.example.vietis.entity.Shop;
import com.example.vietis.inteface.IView;
import com.example.vietis.view_model.SearchActivityModel;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity implements IView {
    //UI holders
    private RecyclerView FoodRecyclerView;
    private NestedScrollView nsvStoreView;
    private TextView txtStoreDescription;
    private TextView txtDescriptionVisible;

    //RecyclerView components
    private ShopAdapter shopAdapter;

    //View Model
    private SearchActivityModel searchActivityModel;

    //local param
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mappingUI();
        setupUI();

    }
    @Override
    public void mappingUI() {
        FoodRecyclerView = findViewById(R.id.FoodRecyclerView);
        shopAdapter = new ShopAdapter(new ArrayList<Shop>());
        searchActivityModel = new ViewModelProvider(this).get(SearchActivityModel.class);
        txtStoreDescription = findViewById(R.id.txtStoreDescription);
        nsvStoreView = findViewById(R.id.nsvStoreView);
        txtDescriptionVisible = findViewById(R.id.txtDescriptionVisible);




        txtStoreDescription.setText(createIndentedText("Với người mới mở quán kinh doanh cafe, viết quảng cáo cho quán cafe là điều không hề dễ dàng. Đặc biệt là tại các thành phố lớn với sự cạnh tranh gay gắt giữa các quán cafe, chi phí đầu tư, thuê mặt bằng đắt đỏ… nếu kinh doanh cafe mà không biết cách quảng cáo mục tiêu của quán đến người dùng, việc kinh doanh lâu dài sẽ gặp nhiều khó khăn. Do đó, bạn cần đầu tư vào việc viết quảng cáo cho quán cafe tốt nhất."
                +"\nTrước tiên để viết được quảng cáo cho quán cafe, bạn cần nắm được ý tưởng và đặc trưng phong cách của quán ví dụ như quán theo phong cách retro, cafe shop, vintage, racer, cafe bụi v.v… Tiếp theo bạn nên thử thưởng thức hương vị của quán để đưa ra đánh giá tốt nhất. Hiện nay, mỗi quán cafe đều có các gu khác nhau, vậy nên bài đánh giá không chỉ nói về không gian, thức uống mà còn cả phong cách phục vụ và tất cả các chi tiết."
                ));
    }
    static String createIndentedText(String text) {
        text = "\t"+text.replaceAll("\n","\n\t");
        return text;
    }
    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        FoodRecyclerView.setLayoutManager(layoutManager);
        searchActivityModel.init();
        searchActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                shopAdapter = new ShopAdapter(arrayList);
                shopAdapter.notifyDataSetChanged();
                FoodRecyclerView.setAdapter(shopAdapter);
                nsvStoreView.scrollTo(0, 0);
            }
        });
        txtDescriptionVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if(isVisible){
                    txtDescriptionVisible.setText("Description ▼");
                    txtStoreDescription.setVisibility(View.VISIBLE);
                }else{
                    txtDescriptionVisible.setText("Description ►");
                    txtStoreDescription.setVisibility(View.GONE);
                }
            }
        });

    }
}