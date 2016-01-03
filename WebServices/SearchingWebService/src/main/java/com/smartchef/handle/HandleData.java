package com.smartchef.handle;

import java.util.List;
import java.util.Map;

import com.smartchef.model.Meal;
import com.smartchef.model.UserSmartChef;
import com.smartchef.mysql.MySqlImplService;
import com.smartchef.utils.DatabaseUtil;
import com.smartchef.utils.JsonUtil;

public class HandleData {
	MySqlImplService mySqlImplService;

	public HandleData() {
		mySqlImplService = new MySqlImplService();
	}

	public String searchMealName(String mealName) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.searchMealName(
						DatabaseUtil.SEARCH_MEAL_NAME, mealName));
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public String searchFriend(String userName) {
		String result = "";
		mySqlImplService.openConnection();
		List<Map<String, Object>> maps = DatabaseUtil
				.readResultFromDB(mySqlImplService.searchMealName(
						DatabaseUtil.SEARCH_USER_NAME, userName));
		result = JsonUtil.convertObjectToJson(maps);
		mySqlImplService.closeConnection();
		return result;
	}

	public void closeConnection() {
		mySqlImplService.closeConnection();
	}
}
