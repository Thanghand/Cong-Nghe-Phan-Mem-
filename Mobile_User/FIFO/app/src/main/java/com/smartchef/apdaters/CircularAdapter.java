package com.smartchef.apdaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jpardogo.listbuddies.lib.adapters.CircularLoopAdapter;
import com.smartchef.app.AppController;
import com.smartchef.controller.DetailMealActivity;
import com.smartchef.controller.R;
import com.smartchef.controller.ViewUserActivity;
import com.smartchef.model.SessionManager;
import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.HandleDataUtil;
import com.smartchef.utils.LoadContant;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 20-May-15.
 */
public class CircularAdapter extends CircularLoopAdapter {

    private Activity activity;
    private List<Map<String, Object>> meals;
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    Map<String, Object> item;
    private SessionManager sessionManager;

    public CircularAdapter(Activity activity, List<Map<String, Object>> meals) {
        this.activity = activity;
        this.meals = meals;
        sessionManager = new SessionManager(activity);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCircularPosition(int position) {
        return super.getCircularPosition(position);
    }

    @Override
    protected int getCircularCount() {
        return meals.size();
    }


    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return meals.get(getCircularPosition(position));
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_meal_list, null);
        }
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView mealPicture = (NetworkImageView) convertView.findViewById(R.id.mealPicture);
        TextView mealName = (TextView) convertView.findViewById(R.id.mealName);
        TextView numberLike = (TextView) convertView.findViewById(R.id.numberLike);
        TextView byUser = (TextView) convertView.findViewById(R.id.byUser);
        CircleImageView pictureByUser = (CircleImageView) convertView.findViewById(R.id.iconUser);

        //Meal item = meals.get(getCircularPosition(position));
        item = meals.get(getCircularPosition(position));
        pictureByUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewUserActivity.class);
                UserSmartChef userSmartChef = new UserSmartChef();
                userSmartChef.setFullName(item.get("fullName").toString());
                userSmartChef.setEmail(item.get("email").toString());
                userSmartChef.setFlagUser(LoadContant.FLAG_ANOTHER_USER);
                String profileUrl = item.get("profilePicture").toString();
                if (profileUrl != null)
                    userSmartChef.addNewImageProfile(profileUrl);
                intent.putExtra("ViewUser", userSmartChef);
                activity.startActivity(intent);
            }
        });
        mealPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailMealActivity.class);
                intent.putExtra("MealID", HandleDataUtil.convertObjectDoubleToInteger(item.get("mealID")));
                intent.putExtra("Email", sessionManager.getUserDetail().getEmail());
                activity.startActivity(intent);
            }
        });
        mealName.setText(item.get("mealNameVN").toString());
        numberLike.setText(HandleDataUtil.convertObjectDoubleToInteger(item.get("numberOfLike")) + " likes");
        byUser.setText(item.get("fullName").toString());
        if (item.get("mealPicture") != null) {
            mealPicture.setImageUrl(item.get("mealPicture").toString(), imageLoader);
        }
        return convertView;
    }
}
