package com.example.vietis.Data.IRepository.repository;


import com.example.vietis.Data.IRepository.ICommentRepository;
import com.example.vietis.Data.IRepository.IStoreDeatilRepository;
import com.example.vietis.Data.entity.Comment;
import com.example.vietis.Data.entity.Rating;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.helpers.API;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StoreRepository {
    private static StoreRepository instance = null;
    private ICommentRepository iCommentRepository;
    private IStoreDeatilRepository iStoreDeatilRepository;
    private List<Object> dataStore;


    /**
     * Constructor
     *
     * @param ICommentRepository
     * @param iStoreDeatilRepository
     */
    private StoreRepository(ICommentRepository ICommentRepository, IStoreDeatilRepository iStoreDeatilRepository) {
        this.iCommentRepository = ICommentRepository;
        this.iStoreDeatilRepository = iStoreDeatilRepository;
    }

    public static StoreRepository getInstance(ICommentRepository ICommentRepository, IStoreDeatilRepository iStoreDeatilRepository) {
        if (instance == null) {
            instance = new StoreRepository(ICommentRepository, iStoreDeatilRepository);
        }
        return instance;
    }


    /**
     * @param token
     * @param id
     */
    public void getDetailStore(String token, String id) {
        //Prepare
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .build();
        Request request = new Request.Builder()
                .url(API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_DETAIL_STORE)))
                .addHeader("tokenkey", token)
                .post(requestBody)
                .build();

        //Execute
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException ioException) {
                /**
                 * Chuyển qua trang error or thoát ứng dụng
                 * */
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String jsonString = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String stringResult = jsonObject.getString("result");
                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                        JSONObject jsonStoreObject = jsonObject.getJSONObject("data");

                        //detail shop
                        Object storeDetailObject = jsonStoreObject.get("foundStore");
                        Shop store = Shop.generateShopFromJSON((JSONObject) storeDetailObject);
                        dataStore.add(store);

                        //rating star
                        for (int i = 5; i > 0; i--) {
                            Object result = jsonStoreObject.get("result" + i);
                            Rating rating = Rating.generateRatingCount(i, (JSONObject) result);
                            dataStore.add(rating);
                        }

                        //3 comments
                        JSONArray CommentObject = jsonStoreObject.getJSONArray("newComment");
                        ArrayList<Comment> listComment = new ArrayList<>();
                        for (int i = 0; i < CommentObject.length(); i++) {
                            Comment com = Comment.generateCommentFroJSon(CommentObject.getJSONObject(i));
                            if (com != null) {
                                listComment.add(com);
                            }
                        }
                        if (listComment.size() > 0) {
                            iCommentRepository.getCommentLimit(listComment, null);
                        } else {
                            iCommentRepository.getCommentLimit(null, new Exception("NO COMMENT"));
                        }
                        iStoreDeatilRepository.getStoreDeatil(dataStore, null);
                    }
                    iStoreDeatilRepository.getStoreDeatil(null, new Exception("GET API FAILED - DATA NULL"));
                } catch (JSONException e) {
                    /**
                     * Chuyển qua trang error or thoát ứng dụng
                     * */
                }
            }
        });
    }
}
