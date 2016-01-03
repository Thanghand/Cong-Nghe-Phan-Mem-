package com.smartchef.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.smartchef.model.UserSmartChef;
import com.smartchef.service.FeedbackActivityService;

/**
 * Created by Administrator on 19-May-15.
 */
public class BaseActionBarActivity extends ActionBarActivity implements FeedbackActivityService {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getFeedBack(String feedBack) {

    }

    @Override
    public UserSmartChef getUserSmartChef() {
        return null;
    }

    @Override
    public void setUserSmartChef(UserSmartChef userSmartChef) {

    }

    @Override
    public String getFlagUser() {
        return null;
    }

    @Override
    public void setFlagUser(String flagUser) {

    }
}
