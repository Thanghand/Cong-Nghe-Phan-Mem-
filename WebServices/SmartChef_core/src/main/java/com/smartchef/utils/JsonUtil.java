package com.smartchef.utils;

import com.google.gson.Gson;

public class JsonUtil {
	public static String convertObjectToJson(Object object) {
		String result = "";
		Gson gson = new Gson();
		result = gson.toJson(object);
		return result;
	}

	public static <T> Object convertJsonToObject(String json, Class<T> type) {
		Gson gson = new Gson();
		Object object = gson.fromJson(json, type);
		return object;
	}
}
