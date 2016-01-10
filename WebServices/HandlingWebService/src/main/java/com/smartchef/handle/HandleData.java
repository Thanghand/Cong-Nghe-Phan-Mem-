package com.smartchef.handle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartchef.mysql.MySqlImplService;
import com.smartchef.utils.DatabaseUtil;
import com.smartchef.utils.JsonUtil;
import com.smartchef.utils.LoadConstant;

public class HandleData {
	MySqlImplService mySqlImplService;

	public HandleData() {
		mySqlImplService = new MySqlImplService();
	}

	public String getSuggestListMeal(String email) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil.learnGetListMeal(email,
				mySqlImplService);
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public String getDetailMeal(String id, String email) {
		String result = "";
		mySqlImplService.openConnection();
		Map<String, Object> resultDetail = mySqlImplService.getDetailMeal(
				DatabaseUtil.GET_DETAIL_MEAL, id, email);
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB((ResultSet) resultDetail.get("ResultSetMeal"));
		String statusLikeMeal = (String) resultDetail.get("StatusLikeMeal");
		maps.get(0).put("StatusLikeMeal", statusLikeMeal);
		// Only one value => default position is 0 .
		result = JsonUtil.convertObjectToJson(maps.get(0));
		mySqlImplService.closeConnection();
		return result;
	}

	public String getMealBaseOnTypeMeal(String typeMeal) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService
						.getMealBaseOnTypeMeal(typeMeal));
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public String getMealBaseOnDietMeal(String dietMeal) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService
						.getMealBaseOnDietMeal(dietMeal));
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public String actionLikes(String action, String mealID, String email) {
		mySqlImplService.openConnection();
		String result = mySqlImplService.actionLikes(action, mealID, email);
		mySqlImplService.closeConnection();
		return result;
	}

	public String showFavorite(String email) {
		mySqlImplService.openConnection();
		String result = "";
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.getFavoriteMeal(email));
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public String insertSeenHistory(String email, String mealID) {
		mySqlImplService.openConnection();
		mySqlImplService.insertDataToSeenHistory(email, mealID);
		mySqlImplService.closeConnection();
		String result = "Success";
		return result;
	}

	public String updateRating(String mealID, String email, String rating) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.updateRating(mealID, email, rating);
		mySqlImplService.closeConnection();
		return result;
	}

	public void CloseConnection() {
		mySqlImplService.closeConnection();
	}

	public String showAllMealOfAnotherUser(String email) {
		mySqlImplService.openConnection();
		String result = "";
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.getRatingofAllUser());
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public void suggestMealForUser(String email) {
		mySqlImplService.openConnection();
		String result = "";
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.getRatingofAllUser());
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
	}

	// Collection
	public String insertNewCollection(String email, String collectionName,
			String mealList) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.insertNewCollection(email, collectionName,
				mealList);
		mySqlImplService.closeConnection();
		return result;
	}

	public String updateNewMealIntoCollectionName(String email,
			String collectionName, String mealList) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.updateMealIntoCollection(email,
				collectionName, mealList);
		mySqlImplService.closeConnection();
		return result;
	}

	public String updateCollectionName(String email, String collectionName) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.updateCollectionNameOfCollection(email,
				collectionName);
		mySqlImplService.closeConnection();
		return result;
	}

	public String getCollectinNameByType(String email, String type,
			String mealID) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = mySqlImplService
				.showCollectionNameByType(email, type);
		result = JsonUtil.convertObjectToJson(maps);
		if (type.equals(LoadConstant.TYPE_DIALOG)) {
			// Check Meal Has been existed in Collections
			for (Map<String, Object> item : maps) {
				String mealList = item.get("mealList").toString();
				if (mealList.contains(mealID)) {
					result = "[" + JsonUtil.convertObjectToJson("Existed")
							+ "]";
					break;
				}
			}
		}

		mySqlImplService.closeConnection();
		return result;
	}

	public String getMealListFromCollectionName(String email,
			String collectionName) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = mySqlImplService
				.showMealListOfCollectionName(email, collectionName);
		result = JsonUtil.convertObjectToJson(maps);
		System.out.println("Result : " + result);
		mySqlImplService.closeConnection();
		return result;
	}

	// delete collection
	public String deleteCollectionNameFromCollection(String email,
			String collectionName) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.deleteCollectionNameFromCollection(
				collectionName, email);
		mySqlImplService.closeConnection();
		return result;
	}

	// update name collectionName
	public String updateNameOfCollectionName(String email, String oldCollectionName, String newCollectionName) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.updateNameOfCollectionName(oldCollectionName,newCollectionName,
				email);
		mySqlImplService.closeConnection();
		return result;
	}

	// Main Demo
	public static void main(String[] arg0) {
		// String demo = "54, 48 ,56";
		// System.out.println("Demo : " + demo.substring(0,2));
		// MySqlImplService mySqlImplService = new MySqlImplService();
		// String result = "";
		// String email = "thanghandsome1302@gmail.com";
		// String type = "Activity";
		// String mealID = "";
		// String collectionName = "Tap Ta";
		// mySqlImplService.openConnection();
		// List<Map<String, Object>> maps = mySqlImplService
		// .showMealListOfCollectionName(email, collectionName);
		// result = JsonUtil.convertObjectToJson(maps);
		// System.out.println("Result : " + result);
		// mySqlImplService.closeConnection();
		String item = "Tap Ta Ne";
		item = item.replace(" ", "%20");
		System.out.print(item);
	}
}
