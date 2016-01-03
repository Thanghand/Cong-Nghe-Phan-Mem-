package com.smartchef.handle;

import java.util.List;
import java.util.Map;

import com.smartchef.model.UserSmartChef;
import com.smartchef.mysql.MySqlImplService;
import com.smartchef.mysql.MysqlService;
import com.smartchef.utils.DatabaseUtil;
import com.smartchef.utils.JsonUtil;

public class HandleData {
	private MySqlImplService mySqlImplService;

	public HandleData() {
		mySqlImplService = new MySqlImplService();

	}
	
	public String persitData(UserSmartChef userSmartChef) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.persitFbUser(userSmartChef);
		mySqlImplService.closeConnection();
		return result;
	}

	public String updateData(UserSmartChef userSmartChef) {
		String result = "";
		mySqlImplService.openConnection();
		result = mySqlImplService.updateUser(userSmartChef);
		mySqlImplService.closeConnection();
		return result;
	}

	public String getInformationAnotherUser(String emailUser, String anotherUser) {
		String result = "";
		mySqlImplService.openConnection();
		result = JsonUtil.convertObjectToJson(mySqlImplService
				.getInformationAnotherUser(emailUser, anotherUser));
		mySqlImplService.closeConnection();
		return result;
	}

	public String actionFollow(String action, String mainUser,
			String anotherUser) {
		mySqlImplService.openConnection();
		String result = mySqlImplService.actionFollow(action, mainUser,
				anotherUser);
		mySqlImplService.closeConnection();
		return result;

	}

	public String getFollower(String email) {
		mySqlImplService.openConnection();
		List<Map<String, Object>> items = DatabaseUtil
				.readResultFromDB(mySqlImplService.getFollower(email));
		String result = JsonUtil.convertObjectToJson(items);
		mySqlImplService.closeConnection();
		return result;
	}

	public String getFollwing(String email) {
		mySqlImplService.openConnection();
		List<Map<String, Object>> items = DatabaseUtil
				.readResultFromDB(mySqlImplService.getFollowing(email));
		String result = JsonUtil.convertObjectToJson(items);
		mySqlImplService.closeConnection();
		return result;
	}

	public void closeConnection() {
		mySqlImplService.closeConnection();
	}

}
