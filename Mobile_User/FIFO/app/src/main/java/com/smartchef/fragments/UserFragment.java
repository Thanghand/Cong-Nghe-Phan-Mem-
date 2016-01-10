package com.smartchef.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.smartchef.apdaters.OptionUserAdapter;
import com.smartchef.apdaters.UserAdapter;
import com.smartchef.app.AppController;
import com.smartchef.controller.AccountSettingActivity;
import com.smartchef.controller.BaseActionBarActivity;
import com.smartchef.controller.CollectionActivity;
import com.smartchef.controller.R;
import com.smartchef.controller.TitleOptionActivity;
import com.smartchef.model.SessionManager;
import com.smartchef.model.UserSmartChef;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.HandleDataUtil;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.ScalingUtilities;
import com.smartchef.utils.WebServiceUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 03-May-15.
 */
public class UserFragment extends BaseFragment {

    private CircleImageView imageProfile;
    private TextView nameUser;
    private Button btnLogout;
    // private ImageLoader imageLoader;
    private SessionManager sessionManager;
    private ImageView imageBackground;
    private ListView listView;
    private OptionUserAdapter optionUserAdapter;
    private ArrayList<Map<String, String>> listTittle;
    private PostObjectService postObjectService;
    private ImageView backgroundUserProfile;
    private static int RESULTCODE = 1;
    // Dialog
    private Dialog dialogFriends;
    private Button dialogButtonExit;
    private ListView dialogListView;
    private List<Map<String, Object>> dialogItemsFriend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_user, container,
                false);
        setFeedBackActivityService((BaseActionBarActivity) getActivity());

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        // defineDiaLog();
        defineView();
    }

    private void defineView() {
        imageProfile = (CircleImageView) getView().findViewById(R.id.imageUserProfile);
        imageBackground = (ImageView) getView().findViewById(R.id.backgroundUserProfile);
        backgroundUserProfile = (ImageView) getView().findViewById(R.id.backgroundUserProfile);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background1);
        // bitmap = ImageUtil.blurImage(getActivity().getApplicationContext(), bitmap);
        imageBackground.setImageBitmap(bitmap);
        listTittle = new ArrayList<>();
        optionUserAdapter = new OptionUserAdapter(getActivity().getApplicationContext(), listTittle);
        listView = (ListView) getView().findViewById(R.id.listViewItemUser);
        listView.setAdapter(optionUserAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!listTittle.get(position).get(LoadContant.TITLE).equals("My Collections")) {
                    Intent intent = new Intent(getActivity(), TitleOptionActivity.class);
                    intent.putExtra("Email", getFeedBackActivityService().getUserSmartChef().getEmail());
                    intent.putExtra("Tittle", listTittle.get(position).get(LoadContant.TITLE));
                    getActivity().startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), CollectionActivity.class);
                    intent.putExtra("Email", getFeedBackActivityService().getUserSmartChef().getEmail());
                    getActivity().startActivity(intent);
                }

            }
        });
        // GetImage
        nameUser = (TextView) getView().findViewById(R.id.textViewUser);
        btnLogout = (Button) getView().findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logOutUser();
            }
        });
        getDetailUser(LoadContant.FLAG_MAIN_USER);
    }

    public void getDetailUser(String flagUser) {
        String email = "";
        if (flagUser.equals(LoadContant.FLAG_MAIN_USER)) {

        } else if (flagUser.equals(LoadContant.FLAG_ANOTHER_USER)) {

        }
        // Get Views Favorite and History
        if (getFeedBackActivityService().getUserSmartChef() != null) {
            String imageURL = "";
            if (getFeedBackActivityService().getUserSmartChef().getPictureProfile().size() < 2)
                imageURL = getFeedBackActivityService().getUserSmartChef().getCurrentProfile(0);
            else
                imageURL = getFeedBackActivityService().getUserSmartChef().getCurrentProfile(1);
            Log.d("UserFragment", "ProfileUrl: " + imageURL);

            ImageRequest imageRequest = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    imageProfile.setImageBitmap(bitmap);
                    Log.d("UserFragment:", "BackgroudnProfile ");
                    // Scale Bitmap with Screen of device
                    DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                    int height = metrics.heightPixels;
                    //   Toast.makeText(getActivity(), "Width: " + width + "-Height :" + height, Toast.LENGTH_LONG).show();
                    bitmap = ScalingUtilities.createScaledBitmap(bitmap, width, height, ScalingUtilities.ScalingLogic.FIT);
                    backgroundUserProfile.setImageBitmap(bitmap);
                }
            }, 0, 0, null, null);
            AppController.getInstance().addToRequestQueue(imageRequest);
            imageProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AccountSettingActivity.class);
                    UserSmartChef userSmartChef = sessionManager.getUserDetail();
                    Log.d("UserFragment", "User : " + getFeedBackActivityService().getUserSmartChef().getEmail());
                    intent.putExtra("UserSmartChef", userSmartChef);
                    startActivityForResult(intent, RESULTCODE);
                }
            });
            nameUser.setText(getFeedBackActivityService().getUserSmartChef().getFullName());
            email = getFeedBackActivityService().getUserSmartChef().getEmail();
            Log.d("UserFragment", "Email: " + sessionManager.getUserDetail().getEmail());
            Log.d("UserFragment", "Url :" + WebServiceUtil.informationUserPage(sessionManager.getUserDetail().getEmail(), email));
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    WebServiceUtil.informationUserPage(sessionManager.getUserDetail().getEmail(), email), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String jsonObject = response.toString();
                    Map<String, Object> data = (Map<String, Object>) JsonUtil.convertJsonToObject(jsonObject, Map.class);
                    Map<String, String> favortie = new HashMap<String, String>();
                    favortie.put(LoadContant.TITLE, "Favorite");
                    favortie.put(LoadContant.NUMBER, HandleDataUtil.convertObjectDoubleToInteger(data.get("Favorite")) + " Views");
                    Map<String, String> newPost = new HashMap<String, String>();
                    newPost.put(LoadContant.TITLE, "My Collections");
                    newPost.put(LoadContant.NUMBER, HandleDataUtil.convertObjectDoubleToInteger(data.get("MyCollection")) + " Views");
                    listTittle.add(favortie);
                    listTittle.add(newPost);
                    if (getFeedBackActivityService().getUserSmartChef().getFlagUser().equals(LoadContant.FLAG_MAIN_USER)) {
                        btnLogout.setText(getResources().getString(R.string.Logout));
                        btnLogout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sessionManager.logOutUser();
                            }
                        });
                    } else if (getFeedBackActivityService().getUserSmartChef().getFlagUser().equals(LoadContant.FLAG_ANOTHER_USER)) {
                        if (HandleDataUtil.convertObjectDoubleToInteger(data.get("Following")) == 1) {
                            btnLogout.setText("UnFollow");

                        } else {
                            btnLogout.setText("Follow");
                        }
                        btnLogout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String action = "";
                                postObjectService = new PostObjectService();
                                postObjectService.setUrlWebServices(WebServiceUtil.ACTION_FOLLOW);
                                postObjectService.setFeedBackActivityService(getFeedBackActivityService());
                                Map<String, String> inputData = new HashMap<String, String>();
                                inputData.put("mainUser", sessionManager.getUserDetail().getEmail());
                                inputData.put("anotherUser", getFeedBackActivityService().getUserSmartChef().getEmail());
                                if (btnLogout.getText().equals("UnFollow")) {
                                    action = "delete";
                                    btnLogout.setText("Follow");
                                } else if (btnLogout.getText().equals("Follow")) {
                                    action = "insert";
                                    btnLogout.setText("UnFollow");
                                }
                                inputData.put("action", action);
                                String json = JsonUtil.convertObjectToJson(inputData);
                                //Toast.makeText(getActivity(), "Follow: " + json, Toast.LENGTH_SHORT).show();
                                Log.d("Follow", "Follow User : " + json);
                                postObjectService.execute(json);
                            }
                        });
                    }
                    optionUserAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //    Toast.makeText(getActivity(), "Error :", Toast.LENGTH_SHORT).show();
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //   Toast.makeText(getActivity(), "ActivityResult", Toast.LENGTH_SHORT).show();
        if (requestCode == RESULTCODE) {
            if (resultCode == getActivity().RESULT_OK) {
                String fullName = data.getExtras().getString("ChangeFullName");
                //  Toast.makeText(getActivity(), "FullName ResultCode : " + fullName, Toast.LENGTH_SHORT).show();
                nameUser.setText(fullName);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
