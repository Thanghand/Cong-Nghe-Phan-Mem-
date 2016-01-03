package com.smartchef.utils;

/**
 * Created by Administrator on 05-May-15.
 */
public class WebServiceUtil {

    public static String IP_SERVER = "http://192.168.1.20";
    public static String USER_WEBSERVICE = IP_SERVER + ":9090/userws/postUser";
    public static String SUGGEST_MEAL = IP_SERVER + ":9091/handlingws/getMeal?email=";
    public static String HANDLING_WEBSERVICE_GET_MEAL_DETAIL = IP_SERVER + ":9091/handlingws/getDetailOfMeal?mealID=";
    public static String UPDATING_USERSMARTCHEF = IP_SERVER + ":9090/userws/updateUser";
    public static String INFORMATION_USER_PAGE = IP_SERVER + ":9090/userws/getDetailUser?";
    public static String ACTION_FOLLOW = IP_SERVER + ":9090/userws/actionFollow";
    public static String GET_MEAL_BASE_ON_TYPE_MEAL = IP_SERVER + ":9091/handlingws/typeMeal?typeMeal=";
    public static String GET_MEAL_BASE_ON_DIET_MEAL = IP_SERVER + ":9091/handlingws/dietMeal?dietMeal=";
    public static String ACTION_LIKE_MEAL = IP_SERVER + ":9091/handlingws/actionLikes";
    public static String SEARCH_MEAL = IP_SERVER + ":9092/searchws/searchMealName?mealName=";
    public static String SHOW_FAVORITE = IP_SERVER + ":9091/handlingws/showFavorite?email=";
    public static String INSERT_SEEN_HISTORY = IP_SERVER + ":9091/handlingws/insertSeenHistoy?";

    public static String informationUserPage(String mainUser, String anotherUser) {
        String keyMainUser = "emailUser=";
        String keyAnotherUser = "anotherUser=";
        return INFORMATION_USER_PAGE + keyMainUser + mainUser + "&" + keyAnotherUser + anotherUser;
    }

    public static String getMealDetail(String mealId, String email) {
        return HANDLING_WEBSERVICE_GET_MEAL_DETAIL + mealId + "&email=" + email;
    }

    public static String insertSeenHistory(String email, String mealID) {
        String keyEmail = "email=";
        String keyMealID = "mealID=";
        return INSERT_SEEN_HISTORY + keyEmail + email + "&" + keyMealID + mealID;
    }


}
