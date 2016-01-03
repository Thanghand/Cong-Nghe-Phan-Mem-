package com.smartchef.utils;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * This class have a lot of methods to handle data or check something in system ..
 * Created by Administrator on 18-Apr-15.
 */
public class HandleDataUtil {

    /**
     * This method use to hide Actionbar of Activity
     *
     * @param activity
     */

    public static void hideActionBar(ActionBarActivity activity) {
        // Check level Version SDK
        if (Build.VERSION.SDK_INT < 16) {
            // Hide actionbar
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getSupportActionBar().hide();
        } else {
            // Hide actionbar
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN);
            activity.getActionBar().hide();
        }
    }

    public static int convertObjectDoubleToInteger(Object number) {
        if (number != null) {
            Double result = (Double) number;
            return result.intValue();
        }
        return 0;
    }
}
