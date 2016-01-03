package com.smartchef.apdaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.smartchef.app.AppController;
import com.smartchef.controller.BaseActionBarActivity;
import com.smartchef.controller.R;
import com.smartchef.customview.CircularNetworkImageView;
import com.smartchef.model.SessionManager;
import com.smartchef.service.PostObjectService;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.WebServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 21-Jun-15.
 */
public class UserAdapter extends BaseAdapter {

    private BaseActionBarActivity activity;
    private List<Map<String, Object>> items;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private PostObjectService postObjectService;
    private SessionManager sessionManager;

    public UserAdapter(BaseActionBarActivity activity, List<Map<String, Object>> items) {
        this.activity = activity;
        this.items = items;
        postObjectService = new PostObjectService();
        sessionManager = new SessionManager(this.activity);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list_user, null);
        }
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        final Map<String, Object> item = items.get(position);
        CircularNetworkImageView circularNetworkImageView = (CircularNetworkImageView) convertView.findViewById(R.id.userImageFollows);
        TextView txtNameUser = (TextView) convertView.findViewById(R.id.nameUserFollows);
        Button btnFollow = (Button) convertView.findViewById(R.id.btnStatusFollow);
        String profileUrl = item.get("profilePicture").toString();
        List<String> profileUrls = (List<String>) JsonUtil.convertJsonToObject(profileUrl, List.class);
        circularNetworkImageView.setImageUrl(profileUrls.get(0), imageLoader);
        txtNameUser.setText(item.get("fullName").toString());
        if (item.get("statusLike").equals("Following")) {
            btnFollow.setText("Dang Theo doi");
            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postObjectService.setFeedBackActivityService(activity);
                    postObjectService.setUrlWebServices(WebServiceUtil.ACTION_FOLLOW);
                    Map<String, String> inputData = new HashMap<String, String>();
                    inputData.put("mainUser", sessionManager.getUserDetail().getEmail());
                    inputData.put("anotherUser", item.get("email").toString());
                    inputData.put("action", "delete");
                    String inputJson = JsonUtil.convertObjectToJson(inputData);
                    postObjectService.execute(inputJson);
                }
            });
        } else if (item.get("statusLike").equals("UnFollow")) {
            btnFollow.setText("Theo doi");
            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postObjectService.setFeedBackActivityService(activity);
                    postObjectService.setUrlWebServices(WebServiceUtil.ACTION_FOLLOW);
                    Map<String, String> inputData = new HashMap<String, String>();
                    inputData.put("mainUser", sessionManager.getUserDetail().getEmail());
                    inputData.put("anotherUser", item.get("email").toString());
                    inputData.put("action", "insert");
                    String inputJson = JsonUtil.convertObjectToJson(inputData);
                    postObjectService.execute(inputJson);
                }
            });
        }


        return convertView;
    }
}
