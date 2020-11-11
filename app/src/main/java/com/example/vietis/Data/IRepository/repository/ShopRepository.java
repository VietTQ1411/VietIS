package com.example.vietis.Data.IRepository.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vietis.Data.IRepository.IStoreRepository;
import com.example.vietis.Data.entity.Shop;
import com.example.vietis.R;
import com.example.vietis.Utilities.common.AppResources;
import com.example.vietis.Utilities.common.UserApp;
import com.example.vietis.Utilities.helpers.API;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ShopRepository {
    private static RequestQueue mRequestQueue;
    private String TAG = "Store Respon";
    private MutableLiveData<ArrayList<Shop>> mutableLiveDataShop = null;
    private static ShopRepository instance;
    private IStoreRepository iShopRepository;


    private ShopRepository(IStoreRepository iShopRepository, MutableLiveData<ArrayList<Shop>> list) {
        this.iShopRepository = iShopRepository;
        mutableLiveDataShop = list;
    }

    public static ShopRepository getInstance(IStoreRepository iShopRepository, MutableLiveData<ArrayList<Shop>> list) {
        if (instance == null) {
            mRequestQueue = Volley.newRequestQueue(AppResources.getContext());
            instance = new ShopRepository(iShopRepository, list);
        }
        return instance;
    }

//    public void getShopPaging(String query, int page) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("search", query)
//                .addFormDataPart("page", page + "")
//                .addFormDataPart("pageNumber", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API))
//                .build();
//        Request request = new Request.Builder()
//                .url(API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_STORE_PAGING)))
//                .addHeader("tokenkey", UserApp.user.getTokenKey())
//                .post(requestBody)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d("Store Ex",e.getMessage());
//                //move Error
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                try {
//                    String jsonString = response.body().string();
//                    JSONObject jsonObject = new JSONObject(jsonString);
//                    String stringResult = jsonObject.getString("result");
//                    if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
//                        JSONArray jsonShopArray = jsonObject.getJSONArray("data");
//                        ArrayList<Shop> list = mutableLiveDataShop.getValue();
//                        for (int i = 0; i < jsonShopArray.length(); i++) {
//                            list.add(Shop.generateShopFromJSON(jsonShopArray.getJSONObject(i)));
//                        }
//                        iShopRepository.getShopData(list,null);
//                    } else {
//                        //move error
//                    }
//                } catch (JSONException e) {
//                    Log.d("Store Ex",e.getMessage());
//                    //move error
//                }
//            }
//        });
//    }

    /**
     * Find any shop match with search String in list shop already get form server
     *
     * @param search
     * @return
     */
    public List<Shop> searchShop(String search) {
        if (search.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Shop> data = mutableLiveDataShop.getValue();
        return data.stream().filter(store -> store.getName().contains(search) ||
                store.getAddress().contains(search) ||
                store.getPhoneNumber().contains(search))
                .collect(Collectors.toList());
    }

    //String request Method.POST
    public void getShopPaging(String query, int page) {
        JSONObject data = new JSONObject();
        try {
            data.put("search", query);
            data.put("page", page);
            data.put("pageNumber", AppResources.getResourses().getString(R.string.NUMBER_ITEM_GET_FORM_API));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API.get_URL_STRING(AppResources.getResourses().getString(R.string.GET_STORE_PAGING)),
                    data, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String stringResult = response.getString("result");
                        if (stringResult.equals(AppResources.getResourses().getString(R.string.SUCCESS_REQUEST))) {
                            JSONArray jsonShopArray = response.getJSONArray("data");
                            ArrayList<Shop> list = mutableLiveDataShop.getValue();
                            for (int i = 0; i < jsonShopArray.length(); i++) {
                                list.add(Shop.generateShopFromJSON(jsonShopArray.getJSONObject(i)));
                            }
                            iShopRepository.getShopData(list, null);
                        } else {
                            //move error
                        }
                    } catch (JSONException e) {
                        Log.d("Store Ex", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
                }
            });
            mRequestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.d("Store Ex", e.getMessage());
        }
    }
}


//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = "MainActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //String request Method.GET
//        volleyStringRequest("NTCDE.COM", "contact@ntcde.com", "https://ntcde.com/wp-content/uploads/2016/12/cropped-2dev4u.comNotes-of-Developer-1.png");
//
//        //JsonObject request Method.GET
//        volleyJsonObjectRequest();
//
//        //JsonArray request Method.GET
//        volleyJsonArrayRequest();
//
//        //Image request
//        volleyImageRequest();
//    }
//
//    private void volleyImageRequest() {
//        ImageRequest imageRequest = new ImageRequest("https://ntcde.com/wp-content/uploads/2016/10/take-a-selfie-with-js.png", new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                Log.e(TAG, "ImageRequest onResponse: " + response.toString());
//            }
//        }, 0, 0, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "ImageRequest onErrorResponse: " + error.getMessage());
//            }
//        });
//        VolleySingleton.getInstance(this).getRequestQueue().add(imageRequest);
//    }
//
//    private void volleyJsonArrayRequest() {
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://dev.ntcde.com/news/api.php", new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Log.e(TAG, "JsonArrayRequest onResponse: " + response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "JsonArrayRequest onErrorResponse: " + error.getMessage());
//            }
//        });
//        VolleySingleton.getInstance(this).getRequestQueue().add(jsonArrayRequest);
//    }
//
//    private void volleyJsonObjectRequest() {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.ipify.org/?format=json", null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.e(TAG, "JsonObjectRequest onResponse: " + response.getString("ip").toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "JsonObjectRequest onErrorResponse: " + error.getMessage());
//            }
//        });
//        VolleySingleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);
//    }
//
//    private void volleyStringRequest(final String name, final String email, final String avatar) {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                "http://dev.ntcde.com/news/api.php?email=" + email,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, response.toString());
//                        if (!response.equals("User doesn't exist")) {
//                            // When user exists will return id_user
//                            Log.e(TAG, "User is exists: id= " + response);
//                        } else {
//                            volleyPostStringRequest(name, email, avatar);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Volley", error.getMessage());
//            }
//        });
//
//        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
//    }
//
//    //String request Method.POST
//    private void volleyPostStringRequest(final String name, final String email, final String avatar) {
//        StringRequest insertRequest = new StringRequest(Request.Method.POST, "http://dev.ntcde.com/news/api.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "StringRequest Post" + response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, error.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", email);
//                params.put("name", name);
//                params.put("avatar", avatar);
//
//                return params;
//            }
//        };
//        VolleySingleton.getInstance(this).getRequestQueue().add(insertRequest);
//    }
//}