package com.smartchef.handle;

import java.sql.ResultSet;
import java.util.ArrayList;
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

	public static void main(String[] arg0) {
		MySqlImplService mySqlImplService = new MySqlImplService();
		String result = "";
		String countMeal = "";
		int count = 0;
		String email = "thanghandsome1302@gmail.com";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.getRatingofAllUser());
		List<Map<String, Object>> mapCount = DatabaseUtil
				.readResultFromDB(mySqlImplService.getCountRatingMeal());
		countMeal = JsonUtil.convertObjectToJson(mapCount.get(0)
				.get("count(*)"));
		count = Integer.parseInt(countMeal);
		System.out.println("Count: " + countMeal);
		result = JsonUtil.convertObjectToJson(maps);

		mySqlImplService.closeConnection();
		System.out.println(maps.size());
		System.out.println(result);
		Map<String, Object> targetUser = null;
		List<Float> listDoTuongDong = new ArrayList<Float>();
		List<Float> listRatingEverage = new ArrayList<Float>();
		for (Map<String, Object> item : maps) {
			if (item.get("email").equals(email)) {
				targetUser = item;
				break;
			}
		}
		maps.remove(targetUser);
		System.out.println(targetUser);
		String item = "1453";
		item = item.substring(item.length() - 1);
		System.out.println(item);
		// Tinh do tuong dong rut rating cua user
		for (int i = 0; i < maps.size(); i++) {
			float doTuongDong = DatabaseUtil.caculateSimilarative(targetUser
					.get("rating").toString(), maps.get(i).get("rating")
					.toString());
			listDoTuongDong.add(doTuongDong);
		}
		List<Float> maxTwoNumber = DatabaseUtil
				.getTwoMaxSimilaritive(listDoTuongDong);
		int indexOfMaxNumberOne = listDoTuongDong.indexOf(maxTwoNumber.get(0));
		int indexOfMaxNumberTwo = listDoTuongDong.indexOf(maxTwoNumber.get(1));
		Map<String, Object> userOne = maps.get(indexOfMaxNumberOne);
		Map<String, Object> userTwo = maps.get(indexOfMaxNumberTwo);
		// Replace 21 = count
		float everageOfItemOne = DatabaseUtil.ratingEverage(
				userOne.get("rating").toString(), 21);
		float everageOfItemTwo = DatabaseUtil.ratingEverage(
				userTwo.get("rating").toString(), 21);
		float everageOfTargetUser = DatabaseUtil.ratingEverage(
				targetUser.get("rating").toString(), 21);
		System.out.println("Rating 1 :" + everageOfItemOne);
		System.out.println("Rating 2 :" + everageOfItemTwo);
		System.out.println("Rating target :" + everageOfTargetUser);
		// Suggest list meal
		// Union listMeal itemOne and itemTwo
		List<String> listMealUnion = new ArrayList<String>();
		String[] arrayItemTemp = userOne.get("rating").toString().split(",");
		for (String itemTemp : arrayItemTemp) {
			itemTemp = DatabaseUtil.getMealIDFromStringItem(itemTemp);
			if (!listMealUnion.contains(itemTemp))
				listMealUnion.add(itemTemp);
		}
		arrayItemTemp = userTwo.get("rating").toString().split(",");
		for (String itemTemp : arrayItemTemp) {
			itemTemp = DatabaseUtil.getMealIDFromStringItem(itemTemp);
			if (!listMealUnion.contains(itemTemp))
				listMealUnion.add(itemTemp);
		}
		// Compare with User Target
		List<String> listMealTarget = new ArrayList<String>();
		arrayItemTemp = targetUser.get("rating").toString().split(",");
		for (String itemTemp : arrayItemTemp) {
			itemTemp = DatabaseUtil.getMealIDFromStringItem(itemTemp);
			if (!listMealTarget.contains(itemTemp))
				listMealTarget.add(itemTemp);
		}
		for (String itemTemp : listMealTarget) {
			if (listMealUnion.contains(itemTemp)) {
				listMealUnion.remove(itemTemp);
			}
		}
		System.out.println(listMealTarget);
		System.out.println(listMealUnion);
		// Predicted rating of meal
		float mauSo = 0;
		for (float doTuongDong : maxTwoNumber) {
			System.out.println("TD :" + doTuongDong);
			mauSo += doTuongDong;
		}
		System.out.println("MauSo : " + mauSo);
		List<String> listPredictedMeal = new ArrayList<String>();
		for (String mealID : listMealUnion) {
			System.out.print("Meal ID : " + mealID + " - ");
			float tuSo = 0;
			float ratingOfMealIdOfItemOne = DatabaseUtil
					.getRatingOfMealFromlistMeal(mealID, userOne.get("rating")
							.toString());
			float ratingOfMealIdOfItemTwo = DatabaseUtil
					.getRatingOfMealFromlistMeal(mealID, userTwo.get("rating")
							.toString());
			tuSo = (maxTwoNumber.get(0) * (ratingOfMealIdOfItemOne - everageOfItemOne))
					+ (maxTwoNumber.get(1) * (ratingOfMealIdOfItemTwo - everageOfItemTwo));
			float ratingMeal = everageOfTargetUser + tuSo / mauSo;
			int currentratingMeal = (int) ratingMeal;
			String suggestMealRating = "0" + mealID + currentratingMeal;
			System.out.println(suggestMealRating);
			if (ratingMeal >= 3.0)
				listPredictedMeal.add(mealID);
		}
		System.out.println(listPredictedMeal);
	}
}
