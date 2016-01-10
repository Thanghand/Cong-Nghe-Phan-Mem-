package com.smartchef.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.smartchef.controller.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 18-Apr-15.
 */
public class LoadContant {
    public static String[] DESCRIPTIONS = {"Học nấu ăn với FIFO", "Bạn sẽ học được nhiều món ăn mới",
            "Tư vấn những món theo sở thích của bạn", " Sưu tầm những món ăn theo nhu cầu của bạn "};
    public static String SUCCESSFULL = "1";
    public static String ERROR = "0";
    public static String COLLECTION_HAS_BEEN_EXITED = "Ten bo suu tap da co";
    public static String TITLE = "Tittle";
    public static String NUMBER = "Number";
    public static String OPITON_MEAL = "OptionMeal";
    public static String COW = "cow";
    public static String PIG = "pig";
    public static String FISH = "fish";
    public static String CHICKEN = "chicken";
    public static String DUCK = "duck";
    public static String IMAGE_RESOURCE = "ImageSource";
    public static String TITLE_FAVORITE = "Favorite";
    public static String FLAG_MAIN_USER = "mainUser";
    public static String FLAG_ANOTHER_USER = "anotherUser";
    public static String BILBOSWASHCAPS = "fonts/Bilbo Swash Caps.ttf";
    public static int KEY_SEARCH_BY_SELECT_OPTION = 1 ;
    public static int KEY_SEARCH_BY_KEY_WORD = 2;
    public static String TYPE_SEARCH = "TypeSearch";
    public static String MEAL_HAS_BEEN_EXISTED_IN_COLLECTION_DIALOG = "Mon an nay ban da ghim roi";
    public static String TYPE_SCREEN_ACTIVITY = "Activity";

    public static String TYPE_MEAL_OPTION = "TypeMeal";
    public static void getHashKey(Context context, PackageManager packageManager) {
        try {

            PackageInfo info = packageManager.getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static void setTypeFaceFExternalFont(String fontPath, TextView view, Activity activity) {
        Typeface tf = Typeface.createFromAsset(activity.getAssets(), fontPath);
        view.setTypeface(tf);
    }
    public static String changeWhiteSpace(String input){
        String result = input.trim();
        result = result.replace(" ", "%20");
        return  result;
    }
}