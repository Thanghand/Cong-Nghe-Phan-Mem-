package com.smartchef.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.smartchef.apdaters.DetailCollectionAdapter;
import com.smartchef.app.AppController;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caothang on 1/4/16.
 */
public class DetailCollectionActivity extends BaseActionBarActivity {
    private ListView listMealOfCollection ;
    private List<Map<String, Object>> collectionMeals;
    private DetailCollectionAdapter detailCollectionAdapter;
    private  String email;
    private String collectionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setCustomView(R.layout.actionbar_collection);
        Toolbar parent = (Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0, 0);
        ImageView backPress = (ImageView) actionBar.getCustomView().findViewById(R.id.collectionBackImage);
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setContentView(R.layout.activity_detail_collection);
        defineViewActivity();
        getIntentData();
        loadMealDataFromWebservices(WebServiceUtil.getDetailCollection(email, collectionName));
    }
    private void getIntentData(){
        email = getIntent().getExtras().getString("Email");
        collectionName  = getIntent().getExtras().getString("CollectionName");
        collectionName = LoadContant.changeWhiteSpace(collectionName);
    }
    private void defineViewActivity(){
        listMealOfCollection = (ListView)findViewById(R.id.listViewDetailCollection);
        collectionMeals  = new ArrayList<>();
        detailCollectionAdapter = new DetailCollectionAdapter(DetailCollectionActivity.this,collectionMeals);
        listMealOfCollection.setAdapter(detailCollectionAdapter);
    }
    public void loadMealDataFromWebservices(String url) {
        JsonArrayRequest mealsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                String jsonObject = obj.toString();
                                Map<String, Object> item = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                                //  Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                                Log.d("DeatailCollection", "Item:" + item);
                                collectionMeals.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        detailCollectionAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(mealsRequest);
    }
}
