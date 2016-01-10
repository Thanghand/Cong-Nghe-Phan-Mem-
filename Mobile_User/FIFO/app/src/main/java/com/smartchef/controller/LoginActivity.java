package com.smartchef.controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.smartchef.model.SessionManager;
import com.smartchef.model.UserSmartChef;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.MyAnimationUtil;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseFragmentActivity {

    private int i = 0;
    private int timeEstimate = 2;
    private int count = 1;
    private Animation fadeIn;
    private Animation fadeOut;
    private TextView description;
    private Button buttonDangNhapFaceBook;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private LoginButton loginButton;
    private PostObjectService postObjectService;
    private UserSmartChef userSmartChef = new UserSmartChef();
    private static boolean ACTIVE_ID_FACEBOOK = true;
    private static int PERMISSION_USER = 2;
    private SessionManager sessionManager;
    private Timer timer;
    private Handler mess;
    private TextView nameTittleApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get Hash Key
        LoadContant.getHashKey(getApplicationContext(), getPackageManager());
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        defineTextView();
        //Defind animation
        defineAnimation();
        // Action Animation
        actionAnimation();
        defineAndDeloyLoginFacebook();
        defineButtonLogin();
        getDataFromIntent();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    /**
     * This method use to load animation fade in and fade out
     */
    private void actionAnimation() {
        int delay = 0;
        int period = 3000;

        timer = new Timer();
        mess = new Handler() {

            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                if (msg.what == 0) {

                    if (count == LoadContant.DESCRIPTIONS.length) {
                        timer.cancel();
                    }
                    if (i > 1) {
                        if (timeEstimate == i) {
                            description.setAnimation(fadeOut);
                            description.setText(LoadContant.DESCRIPTIONS[count]);
                            description.setAnimation(fadeIn);
                            fadeIn.reset();
                            fadeOut.reset();
                            fadeIn.start();
                            fadeOut.start();
                            timeEstimate = i + 2;
                            count++;
                        }
                    }
                    i++;

                }

            }
        };

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Log.d("CHT", "Timer");
                mess.sendEmptyMessage(0);
            }
        }, delay, period);
    }

    /**
     * Define Animation  .
     */
    private void defineAnimation() {
        fadeIn = MyAnimationUtil.generateAnimation(getApplicationContext(), android.R.anim.fade_in);
        fadeOut = MyAnimationUtil.generateAnimation(getApplicationContext(), android.R.anim.fade_out);
        fadeIn.setDuration(3000);
        fadeOut.setDuration(3000);
    }

    private void defineAndDeloyLoginFacebook() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "user_photos", "public_profile"));
        //  checkLogin();
        Log.d("LoginAcitivity", "Status : " + loginButton.getText());

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final AccessToken accessToken = loginResult.getAccessToken();

                GraphRequestAsyncTask request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse graphResponse) {

                        String email = user.optString("email");
                        userSmartChef.setEmail(email);
                        sessionManager.updateEmailSeession(email);
                        Log.d("Login", "Request Email: " + userSmartChef);
                        postUserToWebService(userSmartChef);

                    }
                }).executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "KeyHash 0:",
                        Toast.LENGTH_SHORT).show();
            }
        });
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
                if (currentProfile != null) {
                    userSmartChef.setFullName(currentProfile.getName());
                    userSmartChef.setFirstName(currentProfile.getFirstName());
                    userSmartChef.setLastName(currentProfile.getLastName());
                    userSmartChef.setPriID(PERMISSION_USER);
                    userSmartChef.setActive(ACTIVE_ID_FACEBOOK);
                    userSmartChef.addNewImageProfile(currentProfile.getProfilePictureUri(100, 100) + "");
                    userSmartChef.addNewImageProfile(currentProfile.getProfilePictureUri(500, 500) + "");
                    userSmartChef.setFlagUser(LoadContant.FLAG_MAIN_USER);
                    sessionManager.createLoginSession(userSmartChef);
                    Log.d("LoginActivity", "Uri:" + userSmartChef.getPictureProfile());
                }


            }
        };


    }

    private void defineButtonLogin() {
        buttonDangNhapFaceBook = (Button) findViewById(R.id.btnLoginFacebook);
        buttonDangNhapFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();


            }
        });
    }

    private void defineTextView() {
        // Font path

        nameTittleApp = (TextView) findViewById(R.id.nameTittleApp);

        // Loading Font Face
        LoadContant.setTypeFaceFExternalFont(LoadContant.BILBOSWASHCAPS, nameTittleApp, this);
        description = (TextView) findViewById(R.id.descriptionApp);
        description.setText(LoadContant.DESCRIPTIONS[0]);
    }

    private void logoutFacebook() {
        LoginManager.getInstance().logOut();
    }

    private void postUserToWebService(UserSmartChef userSmartChef) {
        // Convert Object to Json
        String json = JsonUtil.convertObjectToJson(userSmartChef);
        Log.d("LoginActivity","Json Data : " + json);
        postObjectService = new PostObjectService();
        postObjectService.setUrlWebServices(WebServiceUtil.USER_WEBSERVICE);
        postObjectService.setFeedBackActivityService(LoginActivity.this);
        //  postObjectService.setActivity(LoginActivity.this);
        postObjectService.execute(json);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Log.d("Login", "Intent != null");
            String logout = intent.getStringExtra("LogoutFacebook");
            Log.d("Login", "Logout:" + logout);
            if (logout != null) {
                if (logout.equals("logout")) {
                    logoutFacebook();
                }
            }
        }
    }

    @Override
    public void getFeedBack(String feedBack) {
        if (!feedBack.equals(LoadContant.ERROR) && !feedBack.isEmpty()) {
            if (!feedBack.equals(LoadContant.SUCCESSFULL)) // != insert  => updata
            {
                sessionManager.updateNameSeession(feedBack);
            }
            Toast.makeText(getApplicationContext(), "Success login:" + feedBack, Toast.LENGTH_SHORT).show();
            sessionManager.checkLogin();
        } else {
            if (feedBack.equals(LoadContant.ERROR) || feedBack.isEmpty())
                logoutFacebook();
            sessionManager.logOutUser();
            Toast.makeText(getApplicationContext(), "Error login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
