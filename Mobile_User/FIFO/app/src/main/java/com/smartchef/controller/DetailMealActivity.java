package com.smartchef.controller;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.smartchef.app.AppController;
import com.smartchef.model.Meal;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    Button btnPin;

    //dialog
    Dialog dialogCollection;
    ArrayList<String> collectionName;
    ArrayAdapter adapterCollection;
    ListView lvCreateTable;
    Button btnCreateTable;
    LinearLayout layoutCreateTable;
    Button btnOK;
    Button btnX;
    Button btnCloseDialog;
    EditText edtNhom;
    TextView txtStatusFeedBack;

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
        btnPin = (Button) findViewById(R.id.buttonPin);


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
        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defineDialog();
                dialogCollection.show();
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

    private void getCollectionNameDialog(String email, String type, String mealID) {
        Log.d("Detail", "Get Dialog Url : " + WebServiceUtil.getCollectionNameDialog(email, type, mealID));
        JsonArrayRequest jsonObjReq = new JsonArrayRequest(WebServiceUtil.getCollectionNameDialog(email, type, mealID),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parsing json
                        Log.d("Response", "Dialog Rs: " + response.toString());
                        if (!response.toString().equals("[\"Existed\"]")) {
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    String jsonObject = obj.toString();
                                    Log.d("Response", "Dialog Rs 1: " + jsonObject);
                                    Map<String, Object> item = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                                    //  Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                                    Log.d("HomeFragment", "HomeFragment:" + item);
                                    collectionName.add(item.get("collectionName").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        } else {
                            btnCreateTable.setVisibility(View.GONE);
                            collectionName.add(LoadContant.MEAL_HAS_BEEN_EXISTED_IN_COLLECTION_DIALOG);
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapterCollection.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void getFeedBack(String feedBack) {
        super.getFeedBack(feedBack);
        Log.d("FeedBackDetailActi", "Feedback : " + feedBack);
        if (feedBack.equals(LoadContant.SUCCESSFULL)) {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        } else if (feedBack.equals(LoadContant.ERROR)) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else if (feedBack.equals(LoadContant.COLLECTION_HAS_BEEN_EXITED)) {
            Toast.makeText(this, LoadContant.COLLECTION_HAS_BEEN_EXITED, Toast.LENGTH_SHORT).show();
            txtStatusFeedBack.setText(LoadContant.COLLECTION_HAS_BEEN_EXITED);
            txtStatusFeedBack.setVisibility(View.VISIBLE);

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

    private void defineDialog() {


        dialogCollection = new Dialog(this);
        dialogCollection.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogCollection.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialogCollection.setContentView(R.layout.dialog_collection_meal);

        btnCreateTable = (Button) dialogCollection.findViewById(R.id.buttonCreateTable);
        btnOK = (Button) dialogCollection.findViewById(R.id.buttonOk);
        btnX = (Button) dialogCollection.findViewById(R.id.buttonX);
        btnCloseDialog = (Button) dialogCollection.findViewById(R.id.dialogCloseButton);
        edtNhom = (EditText) dialogCollection.findViewById(R.id.editTextNhom);
        txtStatusFeedBack = (TextView)dialogCollection.findViewById(R.id.dialogFeedBackStatus);
        collectionName = new ArrayList<>();
        lvCreateTable = (ListView) dialogCollection.findViewById(R.id.listViewCreateDialog);
        layoutCreateTable = (LinearLayout) dialogCollection.findViewById(R.id.layoutCreateTable);
        btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutCreateTable.setVisibility(View.VISIBLE);

            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameCollect = edtNhom.getText().toString();

                if (collectionName == null) {

                    Toast.makeText(DetailMealActivity.this,
                            "Please insert Group's Food Name ",
                            Toast.LENGTH_SHORT).show();

                } else {
                    if (!collectionName.contains(nameCollect)) {
                        collectionName.add(0, nameCollect);
                        adapterCollection.notifyDataSetChanged();
                        Map<String, String> mapInpunt = new HashMap<String, String>();
                        mapInpunt.put("email", email);
                        mapInpunt.put("collectionName", nameCollect);
                        mapInpunt.put("mealList", "");
                        String jsonInput = JsonUtil.convertObjectToJson(mapInpunt);
                        PostObjectService postObjectService = new PostObjectService();
                        postObjectService.setUrlWebServices(WebServiceUtil.INSERT_NEW_COLLECTION);
                        postObjectService.setFeedBackActivityService(DetailMealActivity.this);
                        postObjectService.execute(jsonInput);
                    }
                    else{
                        txtStatusFeedBack.setText(LoadContant.COLLECTION_HAS_BEEN_EXITED);
                        txtStatusFeedBack.setVisibility(View.VISIBLE);
                    }

                }


            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutCreateTable.setVisibility(View.GONE);
                txtStatusFeedBack.setText("");
                txtStatusFeedBack.setVisibility(View.GONE);

            }
        });
        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCollection.dismiss();
            }
        });

        adapterCollection = new ArrayAdapter(DetailMealActivity.this,
                android.R.layout.simple_list_item_1,
                collectionName);
        lvCreateTable.setAdapter(adapterCollection);
        lvCreateTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = collectionName.get(i);
                if (!item.equals(LoadContant.MEAL_HAS_BEEN_EXISTED_IN_COLLECTION_DIALOG)) {
                    Map<String, String> mapInpunt = new HashMap<String, String>();
                    mapInpunt.put("email", email);
                    mapInpunt.put("collectionName", item);
                    mapInpunt.put("mealList", mealID + "");
                    String jsonInput = JsonUtil.convertObjectToJson(mapInpunt);
                    PostObjectService postObjectService = new PostObjectService();
                    postObjectService.setUrlWebServices(WebServiceUtil.UPDATE_MEAL_INTO_COLLECTION);
                    postObjectService.setFeedBackActivityService(DetailMealActivity.this);
                    postObjectService.execute(jsonInput);
                    dialogCollection.dismiss();
                }

            }
        });
        getCollectionNameDialog(email, "Dialog", mealID + "");
    }

}
