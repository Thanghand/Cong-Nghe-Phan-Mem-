package com.smartchef.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.smartchef.mapper.ResultSetMapper;
import com.smartchef.mysql.MySqlImplService;

public class DatabaseUtil {
	public static String CONNECT_TO_DATABASE = "jdbc:mysql://localhost:3306/fifo?user=root&password=1234";
	public static String INSERT_USER = "insert into User (email,privilegeID,firstName,lastName,password,active,fullName,profilePicture)"
			+ "values (?,?,?,?,?,?,?,?) ";
	public static String DRIVER = "com.mysql.jdbc.Driver";
	public static String STORE_PRODUCER_FBUSER = " {call SP_INS_fbUSER (?,?,?,?,?,?,?) }";
	public static String GET_ALL_MEAL = "select mealID, mealNameVN, mealPicture, numberOfLike from meal";
	public static String GET_MEAL = "select mealID, mealNameVN, mealPicture, numberOfLike ,email ,profilePicture , fullName from meal join user where insertedByEmail = email";
	public static String GET_DETAIL_MEAL = "select * from meal where mealID =  ?";
	public static String STORE_UPDATE_FBUSER = "{call SP_Update_fbUSER (?,?,?)}";
	public static String SEARCH_MEAL_NAME = "select mealID, mealNameVN, mealPicture, numberOfLike ,email ,profilePicture,"
			+ "fullName from meal join user where insertedByEmail = email and mealNameVN like ?";
	public static String SEARCH_USER_NAME = "select * from user where fullName like ? ";
	public static String GET_COUNT_FAVOTITE_HISTORY = "{call SP_GET_INFORMATION_ANOTHER_USER (?,?,?)}";
	public static String ACTION_FOLLOW = "{call SP_ACTION_FOLLOW (?,?,?,?)}";
	public static String GET_MEAL_BASE_ON_TYPE_MEAL = "select mealID, mealNameVN, mealPicture, numberOfLike ,email ,profilePicture , fullName "
			+ "from meal join user where insertedByEmail = email and mealTypeID = "
			+ "(select mealTypeID from MEAL_TYPE where mealTypeName = ";
	public static String GET_MEAL_BASE_ON_DIET_MEAL = "select mealID, mealNameVN, mealPicture, numberOfLike ,email ,profilePicture , fullName "
			+ "from meal join user where insertedByEmail = email and meal.dietID ="
			+ "(select dietID from DIET where dietName =  ";
	public static String ACTION_LIKES = "{call SP_LIKE_MEAL (?,?,?,?)}";
	public static String SP_STATUS_LIKES_MEAL = "{call SP_CHECK_STATUS_MEAL_LIKE(?,?,?)}";
	public static String SHOW_FAVORITE_MEAL = " select user.fullName, meal.insertedByEmail ,meal.mealID , meal.mealNameVN,meal.mealPicture"
			+ ",meal.numberOfLike, user.profilePicture  from favorite , user , meal "
			+ " where  user.email = meal.insertedByEmail and meal.mealID = favorite.mealID "
			+ "and  favorite.email = ?";
	public static String SHOW_FOLLOWER = " select user.email, user.fullName ,user.profilePicture from follow , user "
			+ "where  follow.email = user.email and follow.followingEmail = ?";
	public static String SHOW_FOLLOWING = " select user.email, user.fullName ,user.profilePicture from follow , user"
			+ "where follow.followingEmail = user.email and follow.email = ?";
	public static String INSERT_SEEN_HISTORY = "insert into SEEN_HISTORY (email,mealID) values (?,?)";
	public static String INSERT_EMAIL_TO_SEEN_RESULT = "insert into SEEN_RESULT (email) values (?)";
	public static String INSERT_EMAIL_TO_DIETARY_MATRIX = "insert into DIETARY_MATRIX (email) values (?)";
	public static String COUNT_DATA_FROM_SEEN_HISTORY_BASE_ON_EMAIL = "{call SP_COUNT_ALL_SEEN_HISTORY (?,?)}";
	public static String COUNT_DATA_FROM_SEEN_HISTORY_BASE_ON_EMAIL_WITH_CONDITION = "{call SP_COUNT_DIET_TYPE_SEEN_HISTORY(?,?,?)}";
	public static String UPDATE_SEEN_RESULT = "update seen_result set diet = ? ,"
			+ "vegetablefood = ? , gainweight = ? , weightlifting = ? , allSeen = ? "
			+ "where email = ? ";
	public static String UPDATE_DIETARY_MATRIX = "update DIETARY_MATRIX set diet = ? ,"
			+ "vegetablefood = ? , gainweight = ? , weightlifting = ? "
			+ "where email = ? ";
	public static String SHOW_ALL_MEAL_RATING_ANOTHER_USER = "select user.email, user.rating from user";
	public static String INSERT_INTO_MEAL_RATING = "insert into meal_rating (mealID, email, rating) values (?, ?, ?) ";
	public static String COUNT_MEAL_HAS_RATED = "select count(*) from meal where averageRating != 0 ";
	// Collection
	public static String INSERT_NEW_COLLECTION = "insert into collection (email, collectionName, mealList) values (?,?,?)";
	public static String UPDATE_NEW_MEAL_INTO_COLEECTION = "update collection set mealList = ? "
			+ "where email = ? and collectionName =?";
	public static String UPDATE_COLLECTION_NAME = "update collection set collectionName = ? where email = ?";

	public static String SHOW_MEALLIST_OF_COLLECTION = "select collectionName from meal where email = ?";
	public static String SHOW_DETAIL_MEAL_OF_COLLECTION_NAME = "select collectionName, mealList from collection where email = ? and collectionName = ?";

	public static String getMealBaseOnTypeMeal(String query, String typeMeal) {
		return query + "'" + typeMeal + "'" + ");";
	}

	public static String getMealBaseOnDietMeal(String query, String dietMeal) {
		return query + "'" + dietMeal + "'" + ")";
	}

	public static List<Map<String, Object>> readResultFromDB(ResultSet resultSet) {
		List<Map<String, Object>> maps = null;
		try {
			// Map resultSet to List Map
			maps = ResultSetMapper.toList(resultSet);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	// Thuat Toan ALL
	public static List<Map<String, Object>> learnGetListMeal(String email,
			MySqlImplService mySqlImplService) {
		List<Map<String, Object>> outPut = DatabaseUtil
				.readResultFromDB(mySqlImplService.getAllMeal());
		// List<Map<String, Object>> outPut = new ArrayList<Map<String,
		// Object>>();
		//
		// int countSeenHistory =
		// mySqlImplService.countDatafromSeenHistory(email);
		// int countDietSeenHistory = mySqlImplService
		// .countDataFromSeenHistoryWithCodition(email, LoadConstant.DIET);
		// int countVegetableFoodSeenHistory = mySqlImplService
		// .countDataFromSeenHistoryWithCodition(email,
		// LoadConstant.VEGETABLEFOOD);
		// int countGainWeightSeenHistory = mySqlImplService
		// .countDataFromSeenHistoryWithCodition(email,
		// LoadConstant.GAINWEIGHT);
		// System.out.println("GainWeigt : " + countGainWeightSeenHistory);
		// int countWeightLiftingHistory = mySqlImplService
		// .countDataFromSeenHistoryWithCodition(email,
		// LoadConstant.WEIGHTLIFTING);
		//
		// Map<String, Integer> inputData = new HashMap<String, Integer>();
		// inputData.put(LoadConstant.DIET, countDietSeenHistory);
		// inputData
		// .put(LoadConstant.VEGETABLEFOOD, countVegetableFoodSeenHistory);
		// inputData.put(LoadConstant.GAINWEIGHT, countGainWeightSeenHistory);
		// inputData.put(LoadConstant.WEIGHTLIFTING, countWeightLiftingHistory);
		// inputData.put(LoadConstant.ALLSEEN, countSeenHistory);
		// mySqlImplService.updateSeenResult(inputData, email);
		// float ratingDiet = countDietSeenHistory / countSeenHistory;
		// float ratingVegetableFood = countVegetableFoodSeenHistory
		// / countSeenHistory;
		// float ratingGainWeight = countGainWeightSeenHistory /
		// countSeenHistory;
		// float ratingWeightLifting = countWeightLiftingHistory
		// / countSeenHistory;
		// //
		// Map<String, Float> inputDataDietary = new HashMap<String, Float>();
		// inputDataDietary.put(LoadConstant.DIET, ratingDiet);
		// inputDataDietary.put(LoadConstant.VEGETABLEFOOD,
		// ratingVegetableFood);
		// inputDataDietary.put(LoadConstant.GAINWEIGHT, ratingGainWeight);
		// inputDataDietary.put(LoadConstant.WEIGHTLIFTING,
		// ratingWeightLifting);
		// mySqlImplService.updateDietaryMatrix(inputDataDietary, email);
		// // SORT
		// TreeMap<String, Integer> sortedMap = LoadConstant
		// .SortByValue(inputData);
		// for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
		// String key = entry.getKey();
		// List<Map<String, Object>> temp = readResultFromDB(mySqlImplService
		// .getMealBaseOnDietMeal(key));
		// outPut.addAll(temp);
		// }
		return outPut;

	}

	public static float caculateSimilarative(String targetOne, String targetTwo) {
		String[] arrayTargetOne = targetOne.split(",");
		String[] arrayTargetTwo = targetTwo.split(",");
		List<Integer> listMealTargetOne = new ArrayList<Integer>();
		List<Integer> listMealTargetTwo = new ArrayList<Integer>();
		for (int i = 0; i < arrayTargetOne.length; i++) {
			String item = arrayTargetOne[i];
			item = item.substring(1, item.length() - 1);
			listMealTargetOne.add(Integer.parseInt(item));
		}
		for (int i = 0; i < arrayTargetTwo.length; i++) {
			String item = arrayTargetTwo[i];
			item = item.substring(1, item.length() - 1);
			listMealTargetTwo.add(Integer.parseInt(item));
		}
		float tuSo = 0;
		for (int i = 0; i < listMealTargetOne.size(); i++) {
			int item = listMealTargetOne.get(i);
			int findIndex = listMealTargetTwo.indexOf(item);
			if (findIndex != -1) {
				// Get rating of target one
				String itemTargetOne = arrayTargetOne[i];
				float itemRatingTargetOne = getRatingFromStringItem(itemTargetOne);
				// Get rating of target two
				String itemTargetTwo = arrayTargetTwo[findIndex];
				float itemRatingTargetTwo = getRatingFromStringItem(itemTargetTwo);
				tuSo += itemRatingTargetOne * itemRatingTargetTwo;
			}
		}
		float mauSo = 1;
		float temp1 = 0;
		float temp2 = 0;
		for (String item : arrayTargetOne) {
			float itemRating = getRatingFromStringItem(item);
			temp1 += itemRating * itemRating;
		}
		for (String item : arrayTargetTwo) {
			float itemRating = getRatingFromStringItem(item);
			temp2 += itemRating * itemRating;
		}
		mauSo = temp1 * temp2;
		mauSo = (float) Math.sqrt((double) mauSo);
		float doTuongDong = tuSo / mauSo;
		return doTuongDong;
	}

	public static float getRatingFromStringItem(String input) {
		return Float.parseFloat(input.substring(input.length() - 1));
	}

	public static String getMealIDFromStringItem(String input) {
		return input.substring(1, input.length() - 1);
	}

	public static float ratingEverage(String input, int count) {
		float result = 0;
		String[] array = input.split(",");
		for (String item : array) {
			result += getRatingFromStringItem(item);
		}
		result = result / count;
		return result;
	}

	public static List<Float> getTwoMaxSimilaritive(List<Float> inputArray) {
		List<Float> output = new ArrayList<Float>();
		List<Float> array = new ArrayList<Float>();
		array.addAll(inputArray);
		while (output.size() < 2) {
			float max = 0;
			for (int i = 0; i < array.size(); i++) {
				float item = array.get(i);
				if (item > max) {
					max = item;
				}
			}
			array.remove(max);
			output.add(max);
		}

		return output;
	}

	public static float getRatingOfMealFromlistMeal(String mealID, String input) {
		float result = 0;
		String[] arrayInput = input.split(",");
		for (String item : arrayInput) {
			String meal = getMealIDFromStringItem(item);
			if (meal.equals(mealID)) {
				result = getRatingFromStringItem(item);
				break;
			}
		}
		return result;
	}
}
