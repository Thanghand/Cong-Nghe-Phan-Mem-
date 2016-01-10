package com.smartchef.utils;

/**
 * Created by Administrator on 05-May-15.
 */
public class WebServiceUtil {


    public static String IP_SERVER = "http://192.168.43.140";
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
    public static String INSERT_NEW_COLLECTION = IP_SERVER +":9091/handlingws/insertNewCollection";
    public static String UPDATE_MEAL_INTO_COLLECTION = IP_SERVER + ":9091/handlingws/newMealCollection";
    public static String GET_COLLECTION_NAME = IP_SERVER + ":9091/handlingws/getCollectionName?email=";
    public static String GET_DETAIL_COLLECTION = IP_SERVER + ":9091/handlingws/getMealListFromCollectionName?email=";
    public static String DELETE_COLLECTION = IP_SERVER + ":9091/handlingws/deleteCollection";
    public static String EDIT_COLLECTION_NAME = IP_SERVER + ":9091/handlingws/updateNameOfCollectionName";

    public static String informationUserPage(String mainUser, String anotherUser) {
        String keyMainUser = "emailUser=";
        String keyAnotherUser = "anotherUser=";
        return INFORMATION_USER_PAGE + keyMainUser + mainUser + "&" + keyAnotherUser + anotherUser;
    }

    public static String getMealDetail(String mealId, String email) {
        return HANDLING_WEBSERVICE_GET_MEAL_DETAIL + mealId + "&email=" + email;
    }


    public static String getCollectionNameDialog(String email, String type, String mealID){
        return GET_COLLECTION_NAME + email + "&type=" + type + "&mealID=" + mealID;
    }
    public static String getDetailCollection (String email, String collectionName){
        return GET_DETAIL_COLLECTION + email +"&collectionName=" + collectionName;
    }
}
