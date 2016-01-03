package com.smartchef.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartchef.handle.HandleData;

@Path("/searchws")
public class HomeController {
	HandleData handleData = new HandleData();

	@GET
	@Path("/searchMealName")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchMeal(@QueryParam("mealName") String mealName) {
		String result = handleData.searchMealName(mealName);
		// Get result
		return result;
	}

	@GET
	@Path("/searchFriends")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String searchFriends(@QueryParam("userName") String userName) {
		String result = handleData.searchFriend(userName);
		// Get result
		return result;
	}

	@GET
	@Path("/closeConnection")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getdata() {
		// Get result
		return "Close Connection";
	}
}
