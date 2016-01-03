package com.smartchef.controller;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smartchef.app.AppController;
import com.smartchef.model.Meal;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.HandleDataUtil;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.MyAnimationUtil;
import com.smartchef.utils.ScalingUtilities;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 27-May-15.
 */
public class DetailMealActivity extends BaseActionBarActivity {

    private TextView mealName;
    private ImageView mealPicture;
    private ImageView iconLike;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mealID;
    private String email;
    private String statusLike = "";
    private static String LIKE = "LIKE";
    private static String UNLIKE = "UNLIKE";
    private TextView btnPrepareDescription;
    private TextView btnPrepareTutorial;
    private LinearLayout layoutViewDetail;
    private RelativeLayout layoutBottomTitle;
    private TextView txtDetailTille;
    private String description;
    private String tutorial;
    private int FLAG_ON = 1;
    private int FLAG_OFF = 0;
    private String userPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  HandleDataUtil.hideActionBar(this);
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
        setContentView(R.layout.activity_detail_meal);
        defineView();
        mealID = getIntent().getExtras().getInt("MealID");
        email = getIntent().getExtras().getString("Email");
        loadDataFromWebService(mealID, email);

    }
    private void defineView() {
        layoutViewDetail = (LinearLayout) findViewById(R.id.layoutDetailMeal);
        layoutBottomTitle = (RelativeLayout) findViewById(R.id.layoutBottomTitle);
        btnPrepareDescription = (TextView) findViewById(R.id.btnPrepareDescription);
        btnPrepareTutorial = (TextView) findViewById(R.id.btnPrepareTutorial);
        btnPrepareTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPrepareTutorial.setTypeface(null, Typeface.BOLD_ITALIC);
                btnPrepareDescription.setTypeface(null, Typeface.ITALIC);
                showLayoutDetail(R.id.btnPrepareTutorial, FLAG_ON);
            }
        });
        btnPrepareDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPrepareDescription.setTypeface(null, Typeface.BOLD_ITALIC);
                btnPrepareTutorial.setTypeface(null, Typeface.ITALIC);
                showLayoutDetail(R.id.btnPrepareDescription, FLAG_ON);
            }
        });
        mealName = (TextView) findViewById(R.id.detailMealName);
        txtDetailTille = (TextView) findViewById(R.id.txtDetail);
        iconLike = (ImageView) findViewById(R.id.iconLike);
        mealPicture = (ImageView) findViewById(R.id.detailMealImage);
        iconLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = "";
                if (statusLike.equals("0")) {
                    action = LIKE;
                    statusLike = "1";

                    iconLike.setImageResource(R.drawable.heart);
                } else if (statusLike.equals("1")) {
                    action = UNLIKE;
                    statusLike = "0";
                    iconLike.setImageResource(R.drawable.heart_w);
                }
                Map<String, String> inputData = new HashMap<String, String>();
                inputData.put("action", action);
                inputData.put("mealID", mealID + "");
                inputData.put("email", email);
                String jsonInput = JsonUtil.convertObjectToJson(inputData);
                PostObjectService postObjectService = new PostObjectService();
                postObjectService.setUrlWebServices(WebServiceUtil.ACTION_LIKE_MEAL);
                postObjectService.setFeedBackActivityService(DetailMealActivity.this);
                postObjectService.execute(jsonInput);
            }
        });

        //   postObjectService.execute();

    }

    private void updateView(Meal meal) {
        if (!meal.getMealPicture().equals(null)) {
            Glide.with(this).load(meal.getMealPicture()).diskCacheStrategy(DiskCacheStrategy.ALL).into(mealPicture);
        }
        mealName.setText(meal.getMealName());
        description = meal.getdescriptionVN();
        tutorial = meal.getTutorialVN();
        //   textViewUserPost.setText(userPost);
        if (statusLike.equals("0")) {
            iconLike.setImageResource(R.drawable.heart_w);
        } else if (statusLike.equals("1")) {
            iconLike.setImageResource(R.drawable.heart);
        }
    }

    private void loadDataFromWebService(int mealID, String email) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                WebServiceUtil.getMealDetail(mealID + "", email), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String jsonObject = response.toString();
                    Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                    statusLike = response.getString("StatusLikeMeal").toString();
                    Log.d("DetailMeal", "StatusLike:" + statusLike);
                    updateView(meal);
                    btnPrepareDescription.performClick();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //       Toast.makeText(getApplicationContext(), "Error :", Toast.LENGTH_SHORT).show();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void getFeedBack(String feedBack) {
        super.getFeedBack(feedBack);
        if (feedBack.equals(LoadContant.SUCCESSFULL)) {
            //   Toast.makeText(this, "Like Ok", Toast.LENGTH_SHORT).show();
        } else if (feedBack.equals(LoadContant.ERROR)) {
            // Toast.makeText(this, "Like Not Ok", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLayoutDetail(int ID, int flag) {

        if (flag == FLAG_ON) {
            switch (ID) {
                case R.id.btnPrepareDescription:
                    txtDetailTille.setText(description);
                    break;
                case R.id.btnPrepareTutorial:
                    txtDetailTille.setText(tutorial);
                    break;
                default:
                    txtDetailTille.setText("");
            }
        }
    }
}
