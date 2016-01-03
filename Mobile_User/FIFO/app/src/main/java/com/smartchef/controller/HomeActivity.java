package com.smartchef.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartchef.apdaters.PagerAdapter;
import com.smartchef.fragments.HomeFragment;
import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.WebServiceUtil;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 19-Apr-15.
 */
public class HomeActivity extends BaseActionBarActivity implements OnMenuItemClickListener {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private UserSmartChef userSmartChef;
    private ImageView imgHome;
    private ImageView imgUser;
    private ImageView imgSearch;
    private List<ImageView> imageViews;
    private static int[] listDrawableGray = {R.drawable.home_empty, R.drawable.user_empty, R.drawable.search_empty};
    private static int[] listDrawablePink = {R.drawable.home_fill, R.drawable.user_fill, R.drawable.search_full_background};
    private String flagUser = "";
    RelativeLayout layoutViewSearch;

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
        //   View customView = getLayoutInflater().inflate(R.layout.actionbar_home, null);
        actionBar.setCustomView(R.layout.actionbar_home);
        Toolbar parent = (Toolbar) actionBar.getCustomView().getParent();
        parent.setContentInsetsAbsolute(0, 0);
        layoutViewSearch = (RelativeLayout) actionBar.getCustomView().findViewById(R.id.layoutSearch);
        layoutViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchingActivity.class);
                intent.putExtra(LoadContant.TYPE_SEARCH, LoadContant.KEY_SEARCH_BY_KEY_WORD);
                startActivity(intent);
            }
        });
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        defineButtonView();
        // Default 0 position.
        changeLayoutButton(0);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                changeLayoutButton(arg0);
                int appearVisibility =  (arg0 == 2) ? View.VISIBLE : View.GONE ;
                layoutViewSearch.setVisibility(appearVisibility);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        getDatafromIntent();
    }

    /**
     * Get data from LoginActivity
     */
    private void getDatafromIntent() {
        Intent intent = getIntent();
        setUserSmartChef((UserSmartChef) intent.getSerializableExtra("UserSmartChef"));
        Log.d("HomeActicity", "UserSmartChef:" + userSmartChef.getFirstName());

    }

    private void defineButtonView() {
//        btnHome = (Button) findViewById(R.id.btnHome);
//        btnUser = (Button) findViewById(R.id.btnUser);
//        btnNews = (Button) findViewById(R.id.btnNews);
//        btnHome.setOnClickListener(buttonClickListener);
//        btnUser.setOnClickListener(buttonClickListener);
//        btnNews.setOnClickListener(buttonClickListener);
//        buttons = new ArrayList<>();
//        buttons.add(btnHome);
//        buttons.add(btnUser);
//        buttons.add(btnNews);

        imgHome = (ImageView) findViewById(R.id.imgHome);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);

        imgHome.setOnClickListener(buttonClickListener);
        imgUser.setOnClickListener(buttonClickListener);
        imgSearch.setOnClickListener(buttonClickListener);
        imageViews = new ArrayList<>();
        imageViews.add(imgHome);
        imageViews.add(imgUser);
        imageViews.add(imgSearch);
    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgHome:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.imgUser:
                    viewPager.setCurrentItem(1, true);
                    break;
                case R.id.imgSearch:
                    viewPager.setCurrentItem(2, true);
                    break;
            }
        }
    };

    /**
     * Change Button layout .
     *
     * @param pos
     */
    private void changeLayoutButton(int pos) {
        for (int i = 0; i < imageViews.size(); i++) {
            ImageView item = imageViews.get(i);
            if (i != pos) {
                Drawable drawable = this.getResources().getDrawable(listDrawableGray[i]);
                item.setImageDrawable(drawable);
            } else if (i == pos) {
                Drawable drawable = this.getResources().getDrawable(listDrawablePink[i]);
                item.setImageDrawable(drawable);
            }
        }
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
    public void setFlagUser(String flagUser) {
        flagUser = flagUser;
    }

    @Override
    public String getFlagUser() {
        return flagUser;
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

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();

    }
}
