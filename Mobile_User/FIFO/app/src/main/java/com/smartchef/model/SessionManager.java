package com.smartchef.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.smartchef.controller.BaseFragmentActivity;
import com.smartchef.controller.HomeActivity;
import com.smartchef.controller.LoginActivity;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;

import java.util.List;

/**
 * Created by Administrator on 10-May-15.
 */
public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Activity activity;
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "SmartChef";
    // KEY EMAIL
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_PROFILE = "pictureProfile";
    private static final String KEY_PROFILE_BACKGROUND = "backgroundProfile";

    public SessionManager(Activity activity) {
        this.activity = activity;
        pref = activity.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(UserSmartChef userSmartChef) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_FULL_NAME, userSmartChef.getFullName());
        if (userSmartChef.getEmail() != null)
            editor.putString(KEY_EMAIL, userSmartChef.getEmail());

        // Storing pictureProfile
        editor.putString(KEY_PROFILE, userSmartChef.getCurrentProfile(0));
        // Storing backgroundProfile
        editor.putString(KEY_PROFILE_BACKGROUND, userSmartChef.getCurrentProfile(1));

        // commit changes
        editor.commit();
    }

    public void updateNameSeession(String fullName) {
        editor.putString(KEY_FULL_NAME, fullName);
        editor.commit();
    }

    public void updateEmailSeession(String email) {
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public void checkLogin() {
        // Check login status
        Log.d("CheckLogin", "Check :" + this.isLoggedIn());
        if (this.isLoggedIn()) {
            Intent intent = new Intent(activity, HomeActivity.class);
            Log.d("SessionManager", getUserDetail().getEmail());
            intent.putExtra("UserSmartChef", getUserDetail());
            intent.putExtra("MainUser", LoadContant.FLAG_MAIN_USER);
            activity.startActivity(intent);
            activity.finish();
        } else
            Log.d("CheckLogin", "Check :" + this.isLoggedIn());

    }

    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    //Get UserDetail
    public UserSmartChef getUserDetail() {
        UserSmartChef userSmartChef = new UserSmartChef();
        userSmartChef.setEmail(pref.getString(KEY_EMAIL, null));
        userSmartChef.setFullName(pref.getString(KEY_FULL_NAME, null));
        userSmartChef.addNewImageProfile(pref.getString(KEY_PROFILE, null));
        userSmartChef.addNewImageProfile(pref.getString(KEY_PROFILE_BACKGROUND, null));
        userSmartChef.setFlagUser(LoadContant.FLAG_MAIN_USER);
        return userSmartChef;
    }

    public void logOutUser() {
        //Clear all data
        editor.clear();
        editor.commit();
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("LogoutFacebook", "logout");
        activity.startActivity(intent);
        activity.finish();
    }
}
