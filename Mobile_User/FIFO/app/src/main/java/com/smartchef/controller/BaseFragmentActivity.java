package com.smartchef.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.smartchef.model.UserSmartChef;
import com.smartchef.service.FeedbackActivityService;

/**
 * Created by Administrator on 10-May-15.
 */
public class BaseFragmentActivity extends FragmentActivity implements FeedbackActivityService {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * GetFeedBack that means : Receive response from service ...
     *
     * @param feedBack
     */
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
