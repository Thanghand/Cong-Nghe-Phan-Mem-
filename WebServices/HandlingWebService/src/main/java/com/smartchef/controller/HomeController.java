package com.smartchef.controller;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartchef.handle.HandleData;
import com.smartchef.utils.JsonUtil;

@Path("/handlingws")
public class HomeController {
	HandleData handleData = new HandleData();

	@GET
	@Path("/getMeal")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getdata(@QueryParam("email") String email) {

		String result = handleData.getSuggestListMeal(email);
		return result;
	}

	@GET
	@Path("/getDetailOfMeal")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getMealData(@QueryParam("mealID") String mealID,
			@QueryParam("email") String email) {
		String result = handleData.getDetailMeal(mealID, email);
		return result;
	}

	@GET
	@Path("/typeMeal")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getMealBaseOnTypeMeal(@QueryParam("typeMeal") String typeMeal) {
		String result = handleData.getMealBaseOnTypeMeal(typeMeal);
		return result;
	}

	@GET
	@Path("/dietMeal")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getMealBaseOnDietMeal(@QueryParam("dietMeal") String dietMeal) {
		String result = handleData.getMealBaseOnDietMeal(dietMeal);
		return result;
	}

	@POST
	@Path("/actionLikes")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String actionLikes(String json) {
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String action = inputs.get("action");
		String mealID = inputs.get("mealID");
		String email = inputs.get("email");
		String result = handleData.actionLikes(action, mealID, email);
		return result;
	}

	@GET
	@Path("/showFavorite")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String showFavorite(@QueryParam("email") String email) {
		String result = handleData.showFavorite(email);
		return result;
	}

	@POST
	@Path("/insertSeenHistoy")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String insertSeenHistory(@QueryParam("email") String email,
			@QueryParam("mealID") String mealID) {
		String result = handleData.insertSeenHistory(email, mealID);
		return result;

	}

	@GET
	@Path("/closeConnection")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String closeConnection(@QueryParam("action") String action) {
		handleData.CloseConnection();
		return "Close connection";
	}

	@POST
	@Path("/updateRating")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String updateRating(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String mealID = inputs.get("mealID");
		String email = inputs.get("email");
		String rating = inputs.get("rating");
		result = handleData.updateRating(mealID, email, rating);
		return result;
	}

	@POST
	@Path("/insertNewCollection")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String insertNewCollection(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String email = inputs.get("email");
		String collectionName = inputs.get("collectionName");
		String mealList = inputs.get("mealList");
		result = handleData
				.insertNewCollection(email, collectionName, mealList);
		return result;
	}

	@POST
	@Path("/newMealCollection")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String insertNewMealIntoCollection(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String email = inputs.get("email");
		String collectionName = inputs.get("collectionName");
		String mealList = inputs.get("mealList");
		result = handleData.updateNewMealIntoCollectionName(email,
				collectionName, mealList);
		return result;
	}

	@POST
	@Path("/updateCollectionName")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String updateCollectionName(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String email = inputs.get("email");
		String collectionName = inputs.get("collectionName");
		result = handleData.updateCollectionName(email, collectionName);
		return result;
	}

	@GET
	@Path("/getCollectionName")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String showCollectionNameByType(@QueryParam("email") String email
			,@QueryParam("type") String type,
			@QueryParam("mealID") String mealID) {
		String result = "";
		result = handleData.getCollectinNameByType(email, type, mealID);
		return result;
	}

	@GET
	@Path("/getMealListFromCollectionName")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getMealListFromCollectionName(@QueryParam("email") String email,
			@QueryParam("collectionName") String collectionName) {
		String result = "";
		result = handleData.getMealListFromCollectionName(email, collectionName);
		return result;
	}
	
	
	@POST
	@Path("/deleteCollection")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String deleteCollection(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String email = inputs.get("email");
		String collectionName = inputs.get("collectionName");
		result = handleData.deleteCollectionNameFromCollection(email, collectionName);
		return result;
	}
	
	@POST
	@Path("/updateNameOfCollectionName")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String updateNameOfCollectionName(String json) {
		String result = "";
		Map<String, String> inputs = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String email = inputs.get("email");
		String oldCollectionName = inputs.get("oldCollectionName");
		String newCollectionName = inputs.get("newCollectionName");
		result = handleData.updateNameOfCollectionName(email, oldCollectionName, newCollectionName);
		return result;
	}
}
