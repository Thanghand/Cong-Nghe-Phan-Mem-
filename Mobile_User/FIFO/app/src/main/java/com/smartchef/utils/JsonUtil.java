package com.smartchef.utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 05-May-15.
 */

public class JsonUtil {
    public static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        if (object != null)
            return gson.toJson(object);
        return null;
    }

    public static <T> Object convertJsonToObject(String json, Class<T> type) {
        Gson gson = new Gson();
        Object object = gson.fromJson(json, type);
        return object;
    }
}
