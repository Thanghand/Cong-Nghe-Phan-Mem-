package com.smartchef.model;

import android.app.Activity;
import android.graphics.Bitmap;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.smartchef.app.AppController;
import com.smartchef.controller.R;
import com.smartchef.utils.ScalingUtilities;

/**
 * Created by thangcao on 26/06/2015.
 */
public class HandlingImage {
    ImageView mImageView;
    View viewContain;
    int hWidth;
    int hHeight;
    Animation animFadein;
    int flagAction = 0;

    public void setFlagAction(int flagAction) {
        this.flagAction = flagAction;
    }

    public int getFlagAction() {
        return this.flagAction;
    }

    public HandlingImage(Activity activity) {
        animFadein = AnimationUtils.loadAnimation(activity,
                R.anim.fade_in);

    }

    public void loadImageFromUrl(ImageView imageView, String url, int width, int height) {
        mImageView = imageView;
        //  this.viewContain = viewContain;
        if (flagAction == 1) // HomeFragment
        {
            hWidth = width / 2 - 10;
        } else {
            hWidth = width - 10; // Others
        }
        if (mImageView != null) {
            mImageView.setAnimation(animFadein);

        }
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        hHeight = (bitmap.getHeight() * hWidth) / (bitmap.getWidth());
                        bitmap = ScalingUtilities.createScaledBitmap(bitmap, hWidth, hHeight, ScalingUtilities.ScalingLogic.FIT);
                        mImageView.setImageBitmap(bitmap);
                        animFadein.start();


                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError
                                                        error) {
                    }
                });
        AppController.getInstance().addToRequestQueue(request);
    }

}
