package com.smartchef.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartchef.model.SessionManager;
import com.smartchef.model.UserSmartChef;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.WebServiceUtil;

/**
 * Created by Administrator on 02-Jun-15.
 */
public class AccountSettingActivity extends BaseActionBarActivity {

    private TextView accountEmail;
    private EditText editTextUserName;
    private EditText editTextBirthDate;
    private Button buttonSave;
    private UserSmartChef userSmartChef;
    private PostObjectService postObjectService;
    private SessionManager sessionManager;

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
        setContentView(R.layout.activity_account_setting);
        defineVIew();
        getUserFromIntent();
    }

    private void defineVIew() {
        sessionManager = new SessionManager(this);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);
        accountEmail = (TextView) findViewById(R.id.fieldUserEmail);
        buttonSave = (Button) findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSmartChef userSmartChef = new UserSmartChef();
                userSmartChef.setFullName(editTextUserName.getText().toString());
                userSmartChef.setEmail(accountEmail.getText().toString());
                updateUserSmartChef(userSmartChef);
                sessionManager.updateNameSeession(userSmartChef.getFullName());
            }
        });
    }

    private void getUserFromIntent() {
        Intent intent = getIntent();
        userSmartChef = (UserSmartChef) intent.getExtras().get("UserSmartChef");
        editTextUserName.setText(userSmartChef.getFullName());
        accountEmail.setText(userSmartChef.getEmail());
    }

    private void updateUserSmartChef(UserSmartChef userSmartChef) {
        String json = JsonUtil.convertObjectToJson(userSmartChef);
        postObjectService = new PostObjectService();
        postObjectService.setUrlWebServices(WebServiceUtil.UPDATING_USERSMARTCHEF);
        postObjectService.setFeedBackActivityService(this);
        postObjectService.execute(json);
    }

    @Override
    public void getFeedBack(String feedBack) {
        if (feedBack.equals(LoadContant.SUCCESSFULL)) {
            Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("ChangeFullName", editTextUserName.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        } else {
            if (feedBack.equals(LoadContant.ERROR)) {
                Toast.makeText(this, "Update error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
