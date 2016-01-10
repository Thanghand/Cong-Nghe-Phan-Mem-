package com.smartchef.mysql;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.smartchef.model.UserSmartChef;

public interface MysqlService {
	/**
	 * Connect to database
	 * 
	 * @param connection
	 */
	public void openConnection();

	/**
	 * Close connection
	 */
	public void closeConnection();

	/**
	 * Check , insert , update Facebook User
	 * 
	 * @param userSmartChef
	 * @return
	 */
	public String persitFbUser(UserSmartChef userSmartChef);

	/**
	 * Get Data meal in Database base on action ( Time , Festival , All , Hot )
	 * .
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet getMealFromAction(String query);

	/**
	 * Get Detail meal
	 * 
	 * @param query
	 * @return
	 */
	public Map<String, Object> getDetailMeal(String query, String id,
			String email);

	/**
	 * 
	 * @param userSmartChef
	 * @return
	 */
	public String updateUser(UserSmartChef userSmartChef);

	/**
	 * 
	 * @param mealName
	 * @return
	 */
	public ResultSet searchMealName(String query, String mealName);

	/**
	 * 
	 * @param query
	 * @param userName
	 * @return
	 */
	public ResultSet searchFriend(String query, String userName);

	/**
	 * 
	 * @param emailUser
	 * @return
	 */
	public Map<String, Integer> getInformationAnotherUser(String emailUser,
			String anotherUser);

	/**
	 * 
	 * @param action
	 * @param mainUser
	 * @param anotherUser
	 * @return
	 */
	public String actionFollow(String action, String mainUser,
			String anotherUser);

	/**
	 * 
	 * @param query
	 * @param typeMeal
	 * @return
	 */
	public ResultSet getMealBaseOnTypeMeal(String typeMeal);

	/**
	 * 
	 * @param query
	 * @param dietMeal
	 * @return
	 */
	public ResultSet getMealBaseOnDietMeal(String dietMeal);

	/**
	 * 
	 * @param action
	 * @param mealID
	 * @param email
	 * @return
	 */
	public String actionLikes(String action, String mealID, String email);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public ResultSet getFavoriteMeal(String email);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public ResultSet getFollower(String email);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public ResultSet getFollowing(String email);

	/**
	 * 
	 * @param email
	 * @param mealID
	 * @return
	 */
	public String insertDataToSeenHistory(String email, String mealID);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public int countDatafromSeenHistory(String email);

	/**
	 * 
	 * @param email
	 * @param typeDiet
	 * @return
	 */
	public int countDataFromSeenHistoryWithCodition(String email,
			String typeDiet);

	/**
	 * 
	 * @param inputData
	 * @return
	 */
	public String updateSeenResult(Map<String, Integer> inputData, String email);

	/**
	 * 
	 * @param inputData , string email 
	 * @return
	 */
	public String updateDietaryMatrix(Map<String, Float> inputData, String email);
	
	/**
	 * This function use to update rating for meal 
	 * @param mealID
	 * @param email
	 * @param rating
	 * @return
	 */
	public String updateRating(String mealID , String email, String rating); 
	/**
	 * 
	 * @param email
	 * @param passWord
	 * @param typeUser
	 * @return
	 */
	public String loginWithAdmin (String email, String passWord , String typeUser);
	
	/**
	 * This function get rating all user
	 */
	public ResultSet getRatingofAllUser();
	
	public ResultSet getCountRatingMeal();
	
	public ResultSet getAllMeal();
	
	// Collection 
	/**
	 * This function use to insert new collection 
	 * @param email
	 * @param collection
	 * @param mealList
	 * @return
	 */
	public String insertNewCollection (String email, String collection, String mealList);
	
	/**
	 * This function use to update mewlList into collection
	 * @param email
	 * @param collection
	 * @param mẽalList
	 * @return
	 */
	public String updateMealIntoCollection (String email, String collection ,String mẽalList);
	
	/**
	 * This function use to update collectionName of collection
	 * @param email
	 * @param collectionName
	 * @return
	 */
	public String updateCollectionNameOfCollection (String email, String collectionName);
	
	/**
	 * This function use to show collectionName
	 * @param email
	 * @param type
	 * @return
	 */
	public List<Map<String,Object>> showCollectionNameByType(String email, String type);
	
	/**
	 * This function use to show Mealist into Collection Name
	 * @param email
	 * @param collectionName
	 * @return
	 */
	public List<Map<String,Object>> showMealListOfCollectionName(String email, String collectionName);
	
	/**
	 * 
	 * @param collectionName
	 * @param email
	 * @return
	 */
	public String deleteCollectionNameFromCollection(String collectionName, String email);
	
	/**
	 * 
	 * @param collectionName
	 * @param email
	 * @return
	 */
	public String updateNameOfCollectionName(String oldCollectionName, String newCollectionName, String email);
}
	