package com.smartchef.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smartchef.apdaters.PagerAdapter;
import com.smartchef.fragments.UserFragment;
import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.LoadContant;

/**
 * Created by Administrator on 08-Jun-15.
 */
public class ViewUserActivity extends BaseActionBarActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private UserSmartChef userSmartChef;

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
        setContentView(R.layout.activity_view_user);
        viewPager = (ViewPager) findViewById(R.id.pagerViewUser);
        getDataFromIntent();
        UserFragment userFragment = new UserFragment();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), userFragment);
        viewPager.setAdapter(pagerAdapter);

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        UserSmartChef userSmartChef = (UserSmartChef) intent.getSerializableExtra("ViewUser");
        setUserSmartChef(userSmartChef);
    }

    @Override
    public UserSmartChef getUserSmartChef() {
        return userSmartChef;
    }

    @Override
    public void setUserSmartChef(UserSmartChef userSmartChef) {
        this.userSmartChef = userSmartChef;
    }

    @Override
    public void getFeedBack(String feedBack) {
        super.getFeedBack(feedBack);
        if (feedBack.equals(LoadContant.SUCCESSFULL)) {
            Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();
        } else {
            if (feedBack.equals(LoadContant.ERROR)) {
                Toast.makeText(this, "Update error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
