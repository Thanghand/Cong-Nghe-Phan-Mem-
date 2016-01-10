package com.smartchef.apdaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.smartchef.controller.DetailMealActivity;
import com.smartchef.controller.R;
import com.smartchef.model.SessionManager;
import com.smartchef.utils.HandleDataUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by caothang on 1/4/16.
 */
public class DetailCollectionAdapter extends BaseAdapter {
    private Activity activity;
    private List<Map<String, Object>> collectionMeals;
    private LayoutInflater inflater;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private Random mRandom;
    private SessionManager sessionManager;
    public DetailCollectionAdapter(Activity activity, List<Map<String, Object>> collectionMeals) {
        this.activity = activity;
        this.sessionManager = new SessionManager(activity);
        this.collectionMeals = collectionMeals;
        this.mRandom = new Random();
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
        return collectionMeals.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;
        if (convertView == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_detail_collection, null);
            holder = new ViewHolder();
            holder.mealPicture = (DynamicHeightImageView) convertView.findViewById(R.id.itemMealOfCollectionMealPicture);
            holder.mealName = (TextView) convertView.findViewById(R.id.itemMealOfCollectionMealName);
            holder.numberLike = (TextView) convertView.findViewById(R.id.itemMealOfCollectionNumberLike);
        } else
            holder = (ViewHolder) convertView.getTag();
        //Meal item = meals.get(getCircularPosition(position));
        final Map<String, Object> item = collectionMeals.get(position);
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
            holder.mealName.setText(item.get("mealNameVN").toString());
            holder.numberLike.setText(HandleDataUtil.convertObjectDoubleToInteger(item.get("numberOfLike")) + " likes");
            if (item.get("mealPicture") != null) {
                Glide.with(activity).load(item.get("mealPicture").toString()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mealPicture);
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


    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }

    static class ViewHolder {
        DynamicHeightImageView mealPicture;
        TextView mealName;
        TextView numberLike;
    }
}
