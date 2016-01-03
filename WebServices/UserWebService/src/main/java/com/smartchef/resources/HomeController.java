package com.smartchef.resources;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smartchef.handle.HandleData;
import com.smartchef.model.UserSmartChef;
import com.smartchef.utils.JsonUtil;

@Path("/userws")
public class HomeController {

	HandleData handleData = new HandleData();

	@GET
	@Path("/closeConnection")
	@Produces(MediaType.APPLICATION_JSON)
	public String getdata() {
		handleData.closeConnection();
		return "Close connection";
	}

	@POST
	@Path("/postUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String postUser(String data) {

		String result = "";

		// Map JSON to OBJECT
		UserSmartChef userSmartChef = (UserSmartChef) JsonUtil
				.convertJsonToObject(data, UserSmartChef.class);
		result = handleData.persitData(userSmartChef);
		return result;
	}

	@POST
	@Path("/updateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser(String data) {

		String result = "";
		UserSmartChef userSmartChef = (UserSmartChef) JsonUtil
				.convertJsonToObject(data, UserSmartChef.class);
		System.out.println("USer :" + userSmartChef.getFullName());
		result = handleData.updateData(userSmartChef);
		return result;
	}

	@GET
	@Path("/getDetailUser")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getDetailUser(@QueryParam("emailUser") String emailUser,
			@QueryParam("anotherUser") String anotherUser) {
		String result = "";
		result = handleData.getInformationAnotherUser(emailUser, anotherUser);
		return result;
	}
         
	@GET
	@Path("/getFollower")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getFollower(@QueryParam("email") String email) {
		String result = "";
		result = handleData.getFollower(email);
		return result;
	}

	@GET
	@Path("/getFollowing")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String getFollowing(@QueryParam("email") String email) {
		String result = "";
		result = handleData.getFollwing(email);
		return result;
	}

	@POST
	@Path("/actionFollow")
	@Produces(MediaType.APPLICATION_JSON)
	public String actionFollow(String json) {
		Map<String, String> data = (Map<String, String>) JsonUtil
				.convertJsonToObject(json, Map.class);
		String action = data.get("action");
		String mainUser = data.get("mainUser");
		String anotherUser = data.get("anotherUser");
		String result = "";
		result = handleData.actionFollow(action, mainUser, anotherUser);
		return result;
	}
}
