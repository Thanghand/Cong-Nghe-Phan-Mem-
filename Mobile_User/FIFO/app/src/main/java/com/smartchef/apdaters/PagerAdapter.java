package com.smartchef.apdaters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.smartchef.fragments.BaseFragment;
import com.smartchef.fragments.HomeFragment;
import com.smartchef.fragments.NewFragment;
import com.smartchef.fragments.UserFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 03-May-15.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles = new String[]{"Trang Chủ", "Cá Nhân", "Tin Tức"};
    ArrayList<BaseFragment> listBaseFragments = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        HomeFragment homeFragment = new HomeFragment();
        UserFragment userFragment = new UserFragment();
        NewFragment newFragment = new NewFragment();
        listBaseFragments.add(homeFragment);
        listBaseFragments.add(userFragment);
        listBaseFragments.add(newFragment);
    }

    public PagerAdapter(FragmentManager fm, BaseFragment fragment) {
        super(fm);
        listBaseFragments.add(fragment);
    }

    @Override
    public BaseFragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return listBaseFragments.get(arg0);
    }

    public BaseFragment getRegisteredFragment(int position) {
        return listBaseFragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listBaseFragments.size();
    }
}
