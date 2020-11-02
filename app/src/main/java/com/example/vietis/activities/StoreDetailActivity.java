package com.example.vietis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.Data.view_model.CommentActivityModel;
import com.example.vietis.Data.view_model.ListActivityModel;
import com.example.vietis.R;
import com.example.vietis.UI.adapter.CommentAdapter;
import com.example.vietis.UI.adapter.SearchAdapter;
import com.example.vietis.UI.dialog.RatingFragment;

import java.util.ArrayList;

// To display a message in the log (logcat)

/**
 *
 */
public class StoreDetailActivity extends AppCompatActivity implements IView {
    /**
     * Local param
     */
    private NestedScrollView nsvStoreView;
    private String TAG = "Store Detail";
    private boolean isVisible = true;

    /**
     * About for store
     */
    private ImageView imageStoreDetailIcon;
    private TextView txtStoreName;
    private TextView txtStoreAddress;
    private TextView txtStorePhone;
    private Button btnOrder;
    private Button btnGGMap;

    /**
     * Rating for store
     */
    private LinearLayout rate;
    private RatingBar ratingBar1;
    private TextView txtRating1;
    private RatingBar ratingBar2;
    private TextView txtRating2;
    private RatingBar ratingBar3;
    private TextView txtRating3;
    private RatingBar ratingBar4;
    private TextView txtRating4;
    private RatingBar ratingBar5;
    private TextView txtRating5;

    /**
     * Comment for store
     */
    private RecyclerView CommentRecyclerView;
    private CommentAdapter commentAdapter;

    /**
     * Description for store
     */
    private TextView txtStoreDescription;
    private TextView txtDescriptionVisible;
    private CommentActivityModel commentActivityModel;

    /**
     * Food of store
     */
    //UI holders
    private RecyclerView FoodRecyclerView;
    //RecyclerView components
    private SearchAdapter searchAdapter;
    //View Model
    private ListActivityModel listActivityModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        mappingUI();
        setUpData();
        setupUI();
    }

    @Override
    public void mappingUI() {
        nsvStoreView = findViewById(R.id.nsvStoreView);

        /**
         * About for store
         */
        imageStoreDetailIcon = findViewById(R.id.imageStoreDetailIcon);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtStoreAddress = findViewById(R.id.txtStoreAddress);
        txtStorePhone = findViewById(R.id.txtStorePhone);
        btnOrder = findViewById(R.id.btnOrder);
        btnGGMap = findViewById(R.id.btnGGMap);

        /**
         * Rating for store
         */
        rate = findViewById(R.id.rate);
        ratingBar1 = findViewById(R.id.ratingBar1);
        txtRating1 = findViewById(R.id.txtRating1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        txtRating2 = findViewById(R.id.txtRating2);
        ratingBar3 = findViewById(R.id.ratingBar3);
        txtRating3 = findViewById(R.id.txtRating3);
        ratingBar4 = findViewById(R.id.ratingBar4);
        txtRating4 = findViewById(R.id.txtRating4);
        ratingBar5 = findViewById(R.id.ratingBar5);
        txtRating5 = findViewById(R.id.txtRating5);

        /**
         * Comment for store
         */
        CommentRecyclerView = findViewById(R.id.CommentRecyclerView);
        commentAdapter = new CommentAdapter(new ArrayList<Comment>());
        commentActivityModel = new ViewModelProvider(this).get(CommentActivityModel.class);

        /**
         * Description for store
         */
        txtStoreDescription = findViewById(R.id.txtStoreDescription);
        txtDescriptionVisible = findViewById(R.id.txtDescriptionVisible);

        /**
         * Food of store
         */
        FoodRecyclerView = findViewById(R.id.FoodRecyclerView);
        searchAdapter = new SearchAdapter(new ArrayList<Shop>());
        listActivityModel = new ViewModelProvider(this).get(ListActivityModel.class);
    }

    /**
     * format description
     * @param description
     * @return
     */
    public String createIndentedText(String description) {
        description = "\t" + description.replaceAll("\n", "\n\t");
        return description;
    }

    @Override
    public void setupUI() {
        txtDescriptionVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if (isVisible) {
                    txtDescriptionVisible.setText("Description ▼");
                    txtStoreDescription.setVisibility(View.VISIBLE);
                } else {
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

    /**
     *
     */
    public void setUpData() {
        /**
         *  get data form API and activity
         */
        Intent parent = getIntent();
        int id = Integer.parseInt(parent.getStringExtra("id"));
        //get User form API



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        FoodRecyclerView.setLayoutManager(layoutManager);
        CommentRecyclerView.setLayoutManager(layoutManager2);

        listActivityModel.init(false, false);
        listActivityModel.getShopData().observe(this, new Observer<ArrayList<Shop>>() {
            @Override
            public void onChanged(ArrayList<Shop> arrayList) {
                searchAdapter = new SearchAdapter(arrayList);
                searchAdapter.notifyDataSetChanged();
                FoodRecyclerView.setAdapter(searchAdapter);
                nsvStoreView.scrollTo(0, 0);
            }
        });
        commentActivityModel.init();
        commentActivityModel.getCommentData().observe(this, new Observer<ArrayList<Comment>>() {
            @Override
            public void onChanged(ArrayList<Comment> arrayList) {
                commentAdapter = new CommentAdapter(arrayList);
                commentAdapter.notifyDataSetChanged();
                CommentRecyclerView.setAdapter(commentAdapter);
                nsvStoreView.scrollTo(0, 0);
            }
        });
        txtStoreDescription.setText(createIndentedText("Với người mới mở quán kinh doanh cafe, viết quảng cáo cho quán cafe là điều không hề dễ dàng. Đặc biệt là tại các thành phố lớn với sự cạnh tranh gay gắt giữa các quán cafe, chi phí đầu tư, thuê mặt bằng đắt đỏ… nếu kinh doanh cafe mà không biết cách quảng cáo mục tiêu của quán đến người dùng, việc kinh doanh lâu dài sẽ gặp nhiều khó khăn. Do đó, bạn cần đầu tư vào việc viết quảng cáo cho quán cafe tốt nhất."
                + "\nTrước tiên để viết được quảng cáo cho quán cafe, bạn cần nắm được ý tưởng và đặc trưng phong cách của quán ví dụ như quán theo phong cách retro, cafe shop, vintage, racer, cafe bụi v.v… Tiếp theo bạn nên thử thưởng thức hương vị của quán để đưa ra đánh giá tốt nhất. Hiện nay, mỗi quán cafe đều có các gu khác nhau, vậy nên bài đánh giá không chỉ nói về không gian, thức uống mà còn cả phong cách phục vụ và tất cả các chi tiết."
        ));
    }
}