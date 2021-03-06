package com.smartchef.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartchef.apdaters.OptionUserAdapter;
import com.smartchef.controller.HomeActivity;
import com.smartchef.controller.R;
import com.smartchef.controller.SearchingActivity;
import com.smartchef.utils.LoadContant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 03-May-15.
 */
public class NewFragment extends BaseFragment {
    ListView listView;
    private ArrayList<Map<String, String>> listTittle;
    OptionUserAdapter optionUserAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        listView = (ListView) getView().findViewById((R.id.searchGridView));
        listTittle = new ArrayList<>();
        // Cow
        Map<String, String> cow = new HashMap<>();
        cow.put(LoadContant.TITLE, "Mon Bo");
        cow.put(LoadContant.OPITON_MEAL, LoadContant.COW);
        cow.put(LoadContant.NUMBER, "");
        cow.put(LoadContant.IMAGE_RESOURCE, R.drawable.cow_food + "");
        cow.put(LoadContant.TYPE_MEAL_OPTION, "typeMeal");
        listTittle.add(cow);
        // Chicken
        Map<String, String> chicken = new HashMap<>();
        chicken.put(LoadContant.TITLE, "Mon Ga");
        chicken.put(LoadContant.NUMBER, "");
        chicken.put(LoadContant.OPITON_MEAL, LoadContant.CHICKEN);
        chicken.put(LoadContant.IMAGE_RESOURCE, R.drawable.chicken_food + "");
        chicken.put(LoadContant.TYPE_MEAL_OPTION, "typeMeal");
        listTittle.add(chicken);
        // Pig
        Map<String, String> pig = new HashMap<>();
        pig.put(LoadContant.TITLE, "Mon Heo");
        pig.put(LoadContant.NUMBER, "");
        pig.put(LoadContant.OPITON_MEAL, LoadContant.PIG);
        pig.put(LoadContant.IMAGE_RESOURCE, R.drawable.pig_food + "");
        pig.put(LoadContant.TYPE_MEAL_OPTION, "typeMeal");
        listTittle.add(pig);
        // Duck
        Map<String, String> duck = new HashMap<>();
        duck.put(LoadContant.TITLE, "Mon Vit");
        duck.put(LoadContant.NUMBER, "");
        duck.put(LoadContant.OPITON_MEAL, LoadContant.DUCK);
        duck.put(LoadContant.IMAGE_RESOURCE, R.drawable.duck_food + "");
        duck.put(LoadContant.TYPE_MEAL_OPTION, "typeMeal");
        listTittle.add(duck);
        // Fish
        Map<String, String> fish = new HashMap<>();
        fish.put(LoadContant.TITLE, "Mon Ca");
        fish.put(LoadContant.NUMBER, "");
        fish.put(LoadContant.OPITON_MEAL, LoadContant.FISH);
        fish.put(LoadContant.IMAGE_RESOURCE, R.drawable.fish_food + "");
        fish.put(LoadContant.TYPE_MEAL_OPTION, "typeMeal");
        listTittle.add(fish);
        // Vegetable
        Map<String, String> vegetable = new HashMap<>();
        vegetable.put(LoadContant.TITLE, "Thuc Vat");
        vegetable.put(LoadContant.NUMBER, "");
        vegetable.put(LoadContant.OPITON_MEAL, "vegetablefood");
        vegetable.put(LoadContant.IMAGE_RESOURCE, R.drawable.vegetablefood + "");
        vegetable.put(LoadContant.TYPE_MEAL_OPTION, "dietMeal");
        listTittle.add(vegetable);
        // Tang Can
        Map<String, String> tangcan = new HashMap<>();
        tangcan.put(LoadContant.TITLE, "Tang Can");
        tangcan.put(LoadContant.NUMBER, "");
        tangcan.put(LoadContant.OPITON_MEAL, "gainweigth");
        tangcan.put(LoadContant.IMAGE_RESOURCE, R.drawable.tangcan + "");
        tangcan.put(LoadContant.TYPE_MEAL_OPTION, "dietMeal");
        listTittle.add(tangcan);
        // Giam Can
        Map<String, String> giamcan = new HashMap<>();
        giamcan.put(LoadContant.TITLE, "Giam Can");
        giamcan.put(LoadContant.NUMBER, "");
        giamcan.put(LoadContant.OPITON_MEAL, "diet");
        giamcan.put(LoadContant.IMAGE_RESOURCE, R.drawable.diet + "");
        giamcan.put(LoadContant.TYPE_MEAL_OPTION, "dietMeal");
        listTittle.add(giamcan);
        optionUserAdapter = new OptionUserAdapter(getActivity().getApplicationContext(), listTittle);
        listView.setAdapter(optionUserAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SearchingActivity.class);
                intent.putExtra(LoadContant.TYPE_SEARCH, LoadContant.KEY_SEARCH_BY_SELECT_OPTION);
                intent.putExtra(LoadContant.OPITON_MEAL, listTittle.get(i).get(LoadContant.OPITON_MEAL));
                intent.putExtra(LoadContant.TYPE_MEAL_OPTION,listTittle.get(i).get(LoadContant.TYPE_MEAL_OPTION));
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_news, container,
                false);
        setFeedBackActivityService((HomeActivity) getActivity());
        return rootView;
    }
}
