package com.smartchef.apdaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.smartchef.controller.CollectionActivity;
import com.smartchef.controller.DetailCollectionActivity;
import com.smartchef.controller.R;
import com.smartchef.utils.HandleDataUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by caothang on 1/4/16.
 */
public class CollectionAdapter extends BaseAdapter {
    private Activity activity;
    private List<Map<String, Object>> collections;
    private LayoutInflater inflater;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private Random mRandom;
    private String email;
    public CollectionAdapter(Activity activity, List<Map<String, Object>> collections,String email) {
        this.activity = activity;
        this.collections = collections;
        this.mRandom = new Random();
        this.email = email;
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
        return collections.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_collection, null);
            holder = new ViewHolder();
            holder.collectionPicture = (DynamicHeightImageView) convertView.findViewById(R.id.collectionImage);
            holder.nameCollection = (TextView) convertView.findViewById(R.id.collectionName);
            holder.layoutDeleteCollection = (ImageView) convertView.findViewById(R.id.layoutDeleteCollection);
            holder.layoutEditCollection = (ImageView) convertView.findViewById(R.id.layoutEditCollection);
        } else
            holder = (ViewHolder) convertView.getTag();

        final Map<String, Object> item = collections.get(position);
        double positionHeight = getPositionRatio(position);
        holder.collectionPicture.setHeightRatio(positionHeight / 2);
        if (item != null) {
            final String collectionName = item.get("collectionName").toString();
            String urlCollectionImage = item.get("collectionPicture").toString();
            if (!collectionName.equals(""))
                holder.nameCollection.setText(item.get("collectionName").toString());

            if (!urlCollectionImage.equals("")) {
                Log.d("Collection Activity ", "Activity: " + activity);
                Glide.with(activity).load(urlCollectionImage).
                        diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.collectionPicture);
            } else {
                holder.collectionPicture.setImageResource(R.drawable.background1);
            }
            holder.collectionPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!item.get("collectionPicture").equals("")) {
                        Intent intent = new Intent(activity, DetailCollectionActivity.class);
                        intent.putExtra("MealID", HandleDataUtil.convertObjectDoubleToInteger(item.get("mealID")));
                        intent.putExtra("Email", email);
                        intent.putExtra("CollectionName", item.get("collectionName").toString());
                        activity.startActivity(intent);
                    }
                }
            });
            holder.layoutDeleteCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CollectionAdapter", "Delete");
                    CollectionActivity collectionActivity = (CollectionActivity) activity;
                    collectionActivity.deleteOrEditCollection("Delete", collectionName, position);
                }
            });
            holder.layoutEditCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CollectionAdapter", "Edit");
                    CollectionActivity collectionActivity = (CollectionActivity) activity;
                    collectionActivity.openEditView(true, position, collectionName);
                    collectionActivity.deleteOrEditCollection("Edit", collectionName, position);
                }
            });
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
        DynamicHeightImageView collectionPicture;
        TextView nameCollection;
        ImageView layoutDeleteCollection;
        ImageView layoutEditCollection;
    }
}
