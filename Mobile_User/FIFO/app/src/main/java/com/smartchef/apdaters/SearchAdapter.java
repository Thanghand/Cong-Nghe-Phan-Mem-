package com.smartchef.apdaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.smartchef.app.AppController;
import com.smartchef.controller.DetailMealActivity;
import com.smartchef.controller.R;
import com.smartchef.controller.ViewUserActivity;
import com.smartchef.customview.CircularNetworkImageView;
import com.smartchef.model.HandlingImage;
import com.smartchef.model.SessionManager;
import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.HandleDataUtil;
import com.smartchef.utils.ImageUtil;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadContant;
import com.smartchef.utils.MyAnimationUtil;
import com.smartchef.utils.ScalingUtilities;

import java.util.List;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 18-Jun-15.
 */
public class SearchAdapter extends BaseAdapter {
    private Activity activity;
    private List<Map<String, Object>> meals;
    private LayoutInflater inflater;
    private int flagHomeFragment = 0;
    private SessionManager sessionManager;
    private int width;
    private int height;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private final Random mRandom;

    public SearchAdapter(Activity activity, List<Map<String, Object>> meals, int flagHomeFragment) {
        this.activity = activity;
        this.meals = meals;
        sessionManager = new SessionManager(activity);
        DisplayMetrics metrics = this.activity.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        this.flagHomeFragment = flagHomeFragment;
        this.mRandom = new Random();
    }

    public void setFlagHomeFragment(int flagHomeFragment) {
        this.flagHomeFragment = flagHomeFragment;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_meal_list, null);
            holder = new ViewHolder();
            holder.mealPicture = (DynamicHeightImageView) convertView.findViewById(R.id.mealPicture);
            holder.mealName = (TextView) convertView.findViewById(R.id.mealName);
            holder.numberLike = (TextView) convertView.findViewById(R.id.numberLike);
            holder.byUser = (TextView) convertView.findViewById(R.id.byUser);
            holder.pictureByUser = (CircularNetworkImageView) convertView.findViewById(R.id.iconUser);
        } else
            holder = (ViewHolder) convertView.getTag();
        //Meal item = meals.get(getCircularPosition(position));
        final Map<String, Object> item = meals.get(position);
        double positionHeight = getPositionRatio(position);
        holder.mealPicture.setHeightRatio(positionHeight / 2);

        if (item != null) {
            holder.mealPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetailMealActivity.class);
                    intent.putExtra("MealID", HandleDataUtil.convertObjectDoubleToInteger(item.get("mealID")));
                    intent.putExtra("Email", sessionManager.getUserDetail().getEmail());
                    //  intent.putExtra("UserPost", item.get("fullName").toString());
                    activity.startActivity(intent);
                }
            });
            // LoadContant.setTypeFaceFExternalFont(LoadContant.BILBOREGULAR, holder.mealName, activity);
            holder.mealName.setText(item.get("mealNameVN").toString());
            holder.numberLike.setText(HandleDataUtil.convertObjectDoubleToInteger(item.get("numberOfLike")) + " likes");
//            holder.byUser.setText(item.get("fullName").toString());
            if (item.get("mealPicture") != null) {
                Glide.with(activity).load(item.get("mealPicture").toString()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mealPicture);
                // holder.mealPicture.setImageResource(R.color.mautrang);
            }
            convertView.setTag(holder);
        }


        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d("SampleAdapter", "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    public void getWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }

    static class ViewHolder {
        DynamicHeightImageView mealPicture;
        TextView mealName;
        TextView numberLike;
        TextView byUser;
        CircularNetworkImageView pictureByUser;
    }
}
