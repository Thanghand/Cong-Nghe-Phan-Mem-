package com.smartchef.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.etsy.android.grid.StaggeredGridView;
import com.smartchef.apdaters.SearchAdapter;
import com.smartchef.app.AppController;
import com.smartchef.controller.HomeActivity;
import com.smartchef.controller.R;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 03-May-15.
 */
public class HomeFragment extends BaseFragment {
    private ArrayList<Button> buttons;
    private List<Map<String, Object>> items;
    private Map<String, Object> meal;

    private String apiUrl = WebServiceUtil.GET_MEAL_BASE_ON_TYPE_MEAL;
    private SearchAdapter searchAdapter;
    private ListView gridViewMeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);
        setFeedBackActivityService((HomeActivity) getActivity());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        defineButton();
        defineListBuddies();
    }

    private void defineButton() {
        buttons = new ArrayList<>();


        changeLayoutButton(0);
    }


    /**
     * Define ListBuddies
     */
    private void defineListBuddies() {
        gridViewMeal = (ListView) getView().findViewById(R.id.listViewMeal);
        items = new ArrayList<>();
        //    HomeFragment
        searchAdapter = new SearchAdapter(getActivity(), items, 1);

        gridViewMeal.setAdapter(searchAdapter);

        loadMealDataFromWebservices(WebServiceUtil.SUGGEST_MEAL + getFeedBackActivityService().getUserSmartChef().getEmail());
    }


    /**
     * This method use to change layout button
     *
     * @param position
     */
    private void changeLayoutButton(int position) {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == position) {
                button.setTextColor(getResources().getColor(R.color.mauden));
                Drawable drawable = getActivity().getResources().getDrawable(R.drawable.button_boder_white);
                button.setBackgroundDrawable(drawable);
            } else {
                button.setTextColor(getResources().getColor(R.color.mautrang));
                Drawable drawable = getActivity().getResources().getDrawable(R.drawable.button_transperent_borderwhite);
                button.setBackgroundDrawable(drawable);
            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    public void loadMealDataFromWebservices(String url) {
        if (items != null) {
            items.clear();
            searchAdapter.notifyDataSetChanged();
        }
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
                                Log.d("HomeFragment", "HomeFragment:" + item);
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
