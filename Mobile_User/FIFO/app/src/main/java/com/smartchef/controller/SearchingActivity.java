package com.smartchef.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

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
 * Created by Administrator on 18-Jun-15.
 */
public class SearchingActivity extends BaseActionBarActivity {
    private SearchView searchView;
    private ListView listView;
    private SearchAdapter searchAdapter;
    private List<Map<String, Object>> meals;
    private String tupeMeal;
    private String typeSuggesstMeal;

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
        setContentView(R.layout.activity_search);
        tupeMeal = "";
        defineView();
        actionListener();
        getDataFromIntent();
//        searchView.setQuery(tupeMeal, false);
//        searchView.setFocusable(true);
//        searchView.setIconified(false);
//        searchView.requestFocusFromTouch();
    }

    private void getDataFromIntent() {
        int typeSearch = getIntent().getExtras().getInt(LoadContant.TYPE_SEARCH);
        tupeMeal = getIntent().getExtras().getString(LoadContant.OPITON_MEAL);
        typeSuggesstMeal = getIntent().getExtras().getString(LoadContant.TYPE_MEAL_OPTION);
        if (typeSearch == LoadContant.KEY_SEARCH_BY_SELECT_OPTION) {
            searchView.setVisibility(View.GONE);
            if (typeSuggesstMeal.equals("typeMeal"))
                loadMealDataFromWebservices(WebServiceUtil.GET_MEAL_BASE_ON_TYPE_MEAL + tupeMeal);
            else {
                loadMealDataFromWebservices(WebServiceUtil.GET_MEAL_BASE_ON_DIET_MEAL + tupeMeal);
            }
        }
    }

    private void defineView() {
        searchView = (SearchView) findViewById(R.id.searchFood);
        changeSearchViewTextColor(searchView);
        listView = (ListView) findViewById(R.id.searchGridView);
        meals = new ArrayList<>();
        searchAdapter = new SearchAdapter(this, meals, 2);
        listView.setAdapter(searchAdapter);
    }

    private void actionListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do your task here
                loadMealDataFromWebservices(WebServiceUtil.SEARCH_MEAL +
                        handleSpaceWhite(searchView.getQuery().toString()));
                return false;
            }

        });
    }

    private void loadMealDataFromWebservices(String url) {

        JsonArrayRequest mealsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (meals != null) {
                            meals.clear();
                            searchAdapter.notifyDataSetChanged();
                        }
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                String jsonObject = obj.toString();
                                Map<String, Object> item = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                                //  Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                                meals.add(item);
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

    private String handleSpaceWhite(String text) {
        return text.replace(" ", "%20");
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
