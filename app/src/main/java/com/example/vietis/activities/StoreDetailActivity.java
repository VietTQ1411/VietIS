package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vietis.Data.inteface.IListView;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.UI.dialog.RatingFragment;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.ListActivityModel;
// To display a message in the log (logcat)

import java.util.ArrayList;

public class StoreDetailActivity extends AppCompatActivity implements IView, IListView {
    //UI holders
    private RecyclerView FoodRecyclerView;
    private NestedScrollView nsvStoreView;
    private TextView txtStoreDescription;
    private TextView txtDescriptionVisible;
    private String TAG = "Store Detail";
    private LinearLayout rate;

    //RecyclerView components
    private SearchAdapter searchAdapter;

    //View Model
    private ListActivityModel listActivityModel;

    //local param
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        mappingUI();
        setupUI();

    }
    @Override
    public void mappingUI() {
        FoodRecyclerView = findViewById(R.id.FoodRecyclerView);
        searchAdapter = new SearchAdapter(this,new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
        txtStoreDescription = findViewById(R.id.txtStoreDescription);
        nsvStoreView = findViewById(R.id.nsvStoreView);
        txtDescriptionVisible = findViewById(R.id.txtDescriptionVisible);
        rate = findViewById(R.id.rate);


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
        listActivityModel.init(false,false);
        listActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                searchAdapter.setShopArray(arrayList);
                searchAdapter.notifyDataSetChanged();
                FoodRecyclerView.setAdapter(searchAdapter);
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
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingFragment dialog = new RatingFragment(StoreDetailActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void navigateToShopDetail(Integer position) {

    }

    @Override
    public void navigateToFoodDetail() {

    }
}