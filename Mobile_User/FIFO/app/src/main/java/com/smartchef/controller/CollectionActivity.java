package com.smartchef.controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.smartchef.apdaters.CollectionAdapter;
import com.smartchef.app.AppController;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.HandleDataUtil;
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
 * Created by caothang on 1/4/16.
 */
public class CollectionActivity extends BaseActionBarActivity {

    private ListView listViewCollection;
    private Button btnCreateTable;
    private LinearLayout layoutCreateTable;
    private Button btnOK;
    private Button btnX;
    private EditText edtNhom;
    private TextView txtStatusFeedBack;
    private List<Map<String, Object>> collections;
    private CollectionAdapter collectionAdapter;
    private String email = "";
    private boolean checkIsEditViewOpen = false;
    private int positionCollection = -1;
    private String chooseCollectionName = "";
    // Dialog
    private Dialog dialog;
    private EditText dialogEditCollection;
    private Button dialogOk;
    private Button dialogbtnClose;
    private String typeAction = "";
    private int choosePosition;

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
        setContentView(R.layout.activity_collection);
        loadDataFromIntent();
        definewViewActivity();

        loadDataCollectionFromUrl(WebServiceUtil.getCollectionNameDialog(email, LoadContant.TYPE_SCREEN_ACTIVITY, ""));
    }

    private void loadDataFromIntent() {
        email = getIntent().getExtras().getString("Email");
    }

    private void definewViewActivity() {
        listViewCollection = (ListView) findViewById(R.id.listViewCollection);

        btnCreateTable = (Button) findViewById(R.id.activityButtonCreateTable);
        btnOK = (Button) findViewById(R.id.activityButtonOk);
        btnX = (Button) findViewById(R.id.activityButtonX);
        edtNhom = (EditText) findViewById(R.id.activityEditTextNhom);
        txtStatusFeedBack = (TextView) findViewById(R.id.activityFeedBackStatus);
        layoutCreateTable = (LinearLayout) findViewById(R.id.activityLayoutCreateTable);

        collections = new ArrayList<>();
        collectionAdapter = new CollectionAdapter(CollectionActivity.this, collections, email);
        listViewCollection.setAdapter(collectionAdapter);
        listViewCollection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> item = collections.get(i);
                if (!item.get("collectionPicture").equals("")) {
                    Intent intent = new Intent(CollectionActivity.this, DetailCollectionActivity.class);
                    intent.putExtra("MealID", HandleDataUtil.convertObjectDoubleToInteger(item.get("mealID")));
                    intent.putExtra("Email", email);
                    intent.putExtra("CollectionName", item.get("collectionName").toString());
                    startActivity(intent);
                }
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutCreateTable.setVisibility(View.GONE);
            }
        });
        btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutCreateTable.setVisibility(View.VISIBLE);
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkExisted = false;
                String nameCollect = edtNhom.getText().toString();
                if (!checkIsEditViewOpen) {
                    if (collections == null) {

                        Toast.makeText(CollectionActivity.this,
                                "Please insert Group's Food Name ",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        for (Map<String, Object> item : collections) {
                            if (item.get("collectionName").toString().equals(nameCollect)) {
                                checkExisted = true;
                                break;
                            }
                        }
                        if (!checkExisted) {
                            Map<String, Object> newCollection = new HashMap<String, Object>();
                            newCollection.put("email", email);
                            newCollection.put("collectionName", nameCollect);
                            newCollection.put("collectionPicture", "");
                            newCollection.put("mealList", "");
                            collections.add(0, newCollection);
                            collectionAdapter.notifyDataSetChanged();
                            Map<String, String> mapInpunt = new HashMap<String, String>();
                            mapInpunt.put("email", email);
                            mapInpunt.put("collectionName", nameCollect);
                            mapInpunt.put("mealList", "");
                            String jsonInput = JsonUtil.convertObjectToJson(mapInpunt);
                            PostObjectService postObjectService = new PostObjectService();
                            postObjectService.setUrlWebServices(WebServiceUtil.INSERT_NEW_COLLECTION);
                            postObjectService.setFeedBackActivityService(CollectionActivity.this);
                            postObjectService.execute(jsonInput);
                            txtStatusFeedBack.setText("");
                            txtStatusFeedBack.setVisibility(View.GONE);
                        } else {
                            txtStatusFeedBack.setText(LoadContant.COLLECTION_HAS_BEEN_EXITED);
                            txtStatusFeedBack.setVisibility(View.VISIBLE);
                        }

                    }
                } else {
                    Map<String, Object> editCollection = collections.get(positionCollection);
                    editCollection.put("collectionName", nameCollect);
                    collectionAdapter.notifyDataSetChanged();
                }


            }
        });
    }

    private void loadDataCollectionFromUrl(String url) {
        JsonArrayRequest mealsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                String jsonObject = obj.toString();
                                Map<String, Object> item = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                                //  Meal meal = (Meal) JsonUtil.convertJsonToObject(jsonObject, Meal.class);
                                Log.d("CollectionActivity", "collections list:" + item);
                                collections.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        collectionAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(mealsRequest);
    }

    public void openEditView(boolean isOpen, int position, String collectionName) {

        checkIsEditViewOpen = isOpen;
        if (isOpen) {
            positionCollection = position;
            btnCreateTable.setVisibility(View.GONE);
            layoutCreateTable.setVisibility(View.VISIBLE);
            edtNhom.setText(collectionName);
        }
    }

    private void defineDialog() {
        dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_edit_collection);
        dialogEditCollection = (EditText) dialog.findViewById(R.id.dialogEditCollection);
        dialogOk = (Button) dialog.findViewById(R.id.dialogButtonSaveChanged);
        dialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typeAction.equals("Delete")) {
                    Map<String, String> mapInpunt = new HashMap<String, String>();
                    mapInpunt.put("email", email);
                    mapInpunt.put("collectionName", chooseCollectionName);
                    String jsonInput = JsonUtil.convertObjectToJson(mapInpunt);
                    PostObjectService postObjectService = new PostObjectService();
                    postObjectService.setUrlWebServices(WebServiceUtil.DELETE_COLLECTION);
                    postObjectService.setFeedBackActivityService(CollectionActivity.this);
                    postObjectService.execute(jsonInput);
                    collections.remove(choosePosition);
                    collectionAdapter.notifyDataSetChanged();
                } else {
                    Map<String, String> mapInpunt = new HashMap<String, String>();
                    String newCollectionName = dialogEditCollection.getText().toString();
                    mapInpunt.put("email", email);
                    mapInpunt.put("newCollectionName", newCollectionName);
                    mapInpunt.put("oldCollectionName", chooseCollectionName);
                    String jsonInput = JsonUtil.convertObjectToJson(mapInpunt);
                    PostObjectService postObjectService = new PostObjectService();
                    postObjectService.setUrlWebServices(WebServiceUtil.EDIT_COLLECTION_NAME);
                    postObjectService.setFeedBackActivityService(CollectionActivity.this);
                    postObjectService.execute(jsonInput);
                    Map<String, Object> newCollect = collections.get(choosePosition);
                    newCollect.put("collectionName",newCollectionName);
                    collections.set(choosePosition,newCollect);
                    collectionAdapter.notifyDataSetChanged();

                }
                dialog.dismiss();
            }
        });
        dialogbtnClose = (Button) dialog.findViewById(R.id.dialogEditCloseButton);
        dialogbtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void deleteOrEditCollection(String type, String collectionName, int position) {
        choosePosition = position;
        typeAction = type;
        chooseCollectionName = collectionName;
        defineDialog();
        if (typeAction.equals("Delete")) {
            dialogEditCollection.setVisibility(View.GONE);
        } else {
            dialogEditCollection.setVisibility(View.VISIBLE);
        }
        dialog.show();
    }
}
