package com.smartchef.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.smartchef.apdaters.SearchAdapter;
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
 * Created by Administrator on 21-Jun-15.
 */
public class TitleOptionActivity extends BaseActionBarActivity {
    ListView listView;
    String email = "";
    String tittle = "";
    // Tittle Favorite
    SearchAdapter searchAdapter;
    List<Map<String, Object>> items;

    // Tittle Follow
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
        actionBar.setCustomView(R.layout.actionbar_child_back);
        Toolbar parent = (Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0, 0);
        ImageView backPress = (ImageView) actionBar.getCustomView().findViewById(R.id.backImage);
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setContentView(R.layout.activity_titleoption);
        getIntentTitle();
        defineView();
        loadMealDataFromWebservices(WebServiceUtil.SHOW_FAVORITE + email);
    }

    private void getIntentTitle() {
        email = getIntent().getExtras().getString("Email");
        tittle = getIntent().getExtras().getString("Tittle");
    }

    private void defineView() {
        listView = (ListView) findViewById(R.id.listViewTitleOption);
        if (tittle.equals(LoadContant.TITLE_FAVORITE)) {
            items = new ArrayList<>();
            searchAdapter = new SearchAdapter(this, items, 2);
            listView.setAdapter(searchAdapter);
        }

    }

    private void loadMealDataFromWebservices(String url) {

        JsonArrayRequest mealsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (items != null) {
                            items.clear();
                            searchAdapter.notifyDataSetChanged();
                        }
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                String jsonObject = obj.toString();
                                Map<String, Object> item = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                                //  Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                                items.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        searchAdapter.notifyDataSetChanged();

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
