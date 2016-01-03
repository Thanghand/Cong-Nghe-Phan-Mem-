package com.smartchef.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.DatabaseUtil;
import com.smartchef.utils.LoadConstant;

public class MySqlImplService implements MysqlService {

	private Connection connect;
	private CallableStatement callableStatement;
	private Statement statement;

	@Override
	public void openConnection() {
		// TODO Auto-generated method stub

		try {
			Class.forName(DatabaseUtil.DRIVER);
			connect = DriverManager
					.getConnection(DatabaseUtil.CONNECT_TO_DATABASE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String persitFbUser(UserSmartChef userSmartChef) {
		// TODO Auto-generated method stub
		String response = "";
		try {
			callableStatement = connect
					.prepareCall(DatabaseUtil.STORE_PRODUCER_FBUSER);
			callableStatement.setString(1, userSmartChef.getEmail());
			callableStatement.setString(2, userSmartChef.getPassword());
			callableStatement.setString(3, userSmartChef.getFirstName());
			callableStatement.setString(4, userSmartChef.getLastName());
			callableStatement.setString(5, userSmartChef.getFullName());
			callableStatement.setString(6, userSmartChef.getProfilePicture()
					+ "");
			// Get Output Parameter
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			callableStatement.execute();
			// Get result
			response = callableStatement.getString(7) + "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResultSet getMealFromAction(String query) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(DatabaseUtil.GET_MEAL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public Map<String, Object> getDetailMeal(String query, String id,
			String email) {
		// TODO Auto-generated method stub
		Map<String, Object> mapDetailMeal = new HashMap<String, Object>();
		ResultSet resultSet = null;
		String statusLikeMeal = "";
		String userPost = "";
		try {
			statement = connect.createStatement();
			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();

			callableStatement = connect
					.prepareCall(DatabaseUtil.SP_STATUS_LIKES_MEAL);
			callableStatement.setString(1, email);
			callableStatement.setString(2, id);
			// Get Output Parameter
			// Status result
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.execute();
			//
			statusLikeMeal = callableStatement.getInt(3) + "";
			mapDetailMeal.put("ResultSetMeal", resultSet);
			mapDetailMeal.put("StatusLikeMeal", statusLikeMeal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapDetailMeal;
	}

	@Override
	public String updateUser(UserSmartChef userSmartChef) {
		// TODO Auto-generated method stub
		String response = "";
		try {
			callableStatement = connect
					.prepareCall(DatabaseUtil.STORE_UPDATE_FBUSER);
			callableStatement.setString(1, userSmartChef.getEmail());
			callableStatement.setString(2, userSmartChef.getFullName());
			// Get Output Parameter
			callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
			callableStatement.execute();
			response = callableStatement.getString(3).toString();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	@Override
	public ResultSet searchMealName(String query, String mealName) {
		// TODO Auto-generated method stub

		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.setString(1, "%" + mealName + "%");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet searchFriend(String query, String userName) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(query);
			preparedStatement.setString(1, "%" + userName + "%");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public Map<String, Integer> getInformationAnotherUser(String emailUser,
			String anotherUser) {
		// TODO Auto-generated method stub
		Map<String, Integer> mapCount = new HashMap<String, Integer>();
		int countFavorite;
		int countHistory;
		int countFollowing;
		int countFollower;
		int statusFollow;
		try {
			callableStatement = connect
					.prepareCall(DatabaseUtil.GET_COUNT_FAVOTITE_HISTORY);
			callableStatement.setString(1, emailUser);
			callableStatement.setString(2, anotherUser);
			// Get Output Parameter
			// Favorite
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.execute();
			// Get result
			countFavorite = callableStatement.getInt(3);
			// Set Value
			mapCount.put(LoadConstant.TITTLE_FAVORITE, countFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapCount;
	}

	@Override
	public String actionFollow(String action, String mainUser,
			String anotherUser) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			callableStatement = connect.prepareCall(DatabaseUtil.ACTION_FOLLOW);
			callableStatement.setString(1, action);
			callableStatement.setString(2, mainUser);
			callableStatement.setString(3, anotherUser);
			// Get Output Parameter
			// response
			callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
			callableStatement.execute();
			// Get result
			result = callableStatement.getInt(4) + "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSet getMealBaseOnTypeMeal(String typeMeal) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(DatabaseUtil
					.getMealBaseOnTypeMeal(
							DatabaseUtil.GET_MEAL_BASE_ON_TYPE_MEAL, typeMeal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getMealBaseOnDietMeal(String dietMeal) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(DatabaseUtil
					.getMealBaseOnDietMeal(
							DatabaseUtil.GET_MEAL_BASE_ON_DIET_MEAL, dietMeal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public String actionLikes(String action, String mealID, String email) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			callableStatement = connect.prepareCall(DatabaseUtil.ACTION_LIKES);
			callableStatement.setString(1, action);
			callableStatement.setString(2, mealID);
			callableStatement.setString(3, email);
			// Get Output Parameter
			// response
			callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
			callableStatement.execute();
			// Get result
			result = callableStatement.getInt(4) + "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultSet getFavoriteMeal(String email) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.SHOW_FAVORITE_MEAL);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getFollower(String email) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.SHOW_FOLLOWER);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getFollowing(String email) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.SHOW_FOLLOWING);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	// Thuat toan khuyen nghi
	@Override
	public String insertDataToSeenHistory(String email, String mealID) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStmt = connect
					.prepareStatement(DatabaseUtil.INSERT_SEEN_HISTORY);
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, mealID);
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countDatafromSeenHistory(String email) {
		// TODO Auto-generated method stub

		int count = 0;
		try {

			callableStatement = connect
					.prepareCall(DatabaseUtil.COUNT_DATA_FROM_SEEN_HISTORY_BASE_ON_EMAIL);
			callableStatement.setString(1, email);
			// Get Output Parameter
			// Count
			callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
			callableStatement.execute();
			// Get result
			count = callableStatement.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int countDataFromSeenHistoryWithCodition(String email,
			String typeDiet) {
		// TODO Auto-generated method stub
		int count = 0;

		try {

			callableStatement = connect
					.prepareCall(DatabaseUtil.COUNT_DATA_FROM_SEEN_HISTORY_BASE_ON_EMAIL_WITH_CONDITION);
			callableStatement.setString(1, email);
			callableStatement.setString(2, typeDiet);
			// Get Output Parameter
			// Count
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.execute();
			// Get result
			count = callableStatement.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String updateSeenResult(Map<String, Integer> inputData, String email) {
		// TODO Auto-generated method stub

		String result = "";
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.UPDATE_SEEN_RESULT);
			preparedStatement.setInt(1, inputData.get(LoadConstant.DIET));
			preparedStatement.setInt(2,
					inputData.get(LoadConstant.VEGETABLEFOOD));
			preparedStatement.setInt(3, inputData.get(LoadConstant.GAINWEIGHT));
			preparedStatement.setInt(4,
					inputData.get(LoadConstant.WEIGHTLIFTING));
			preparedStatement.setInt(5, inputData.get(LoadConstant.ALLSEEN));
			preparedStatement.setString(6, email);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateDietaryMatrix(Map<String, Float> inputData, String email) {
		// TODO Auto-generated method stub
		String result = "";
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.UPDATE_DIETARY_MATRIX);
			preparedStatement.setFloat(1, inputData.get("diet"));
			preparedStatement.setFloat(2, inputData.get("vegetablefood"));
			preparedStatement.setFloat(3, inputData.get("gainweight"));
			preparedStatement.setFloat(4, inputData.get("weightlifting"));
			preparedStatement.setString(5, email);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String updateRating(String mealID, String email, String rating) {
		String result = "";
		try {
			PreparedStatement preparedStmt = connect
					.prepareStatement(DatabaseUtil.INSERT_INTO_MEAL_RATING);
			preparedStmt.setString(1, mealID);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, rating);
			preparedStmt.execute();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String loginWithAdmin(String email, String passWord, String typeUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getRatingofAllUser() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.SHOW_ALL_MEAL_RATING_ANOTHER_USER);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getCountRatingMeal() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.COUNT_MEAL_HAS_RATED);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public ResultSet getAllMeal() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(DatabaseUtil.GET_ALL_MEAL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public String insertNewCollection(String email, String collectionName,
			String mealList) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			PreparedStatement preparedStmt = connect
					.prepareStatement(DatabaseUtil.INSERT_NEW_COLLECTION);
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, collectionName);
			preparedStmt.setString(3, mealList);
			preparedStmt.execute();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateCollectionNameOfCollection(String email,
			String collectionName) {
		// TODO Auto-generated method stub
		String result = "";
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.UPDATE_COLLECTION_NAME);
			preparedStatement.setString(1, collectionName);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateMealIntoCollection(String email, String collectionName,
			String mealList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String result = "";
		try {

			PreparedStatement preparedStatement = connect
					.prepareStatement(DatabaseUtil.UPDATE_NEW_MEAL_INTO_COLEECTION);
			preparedStatement.setString(1, mealList);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, collectionName);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			result = "Success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
