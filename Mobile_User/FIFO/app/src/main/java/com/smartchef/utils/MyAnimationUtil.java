package com.smartchef.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Administrator on 19-Apr-15.
 */
public class MyAnimationUtil {
    public static Animation generateAnimation(Context context, int typeId) {
        Animation animation = null;
        try {
            animation = AnimationUtils.loadAnimation(context,
                    typeId);
        } catch (Exception ex) {
            animation = null;
        }

        return animation;
    }
}
