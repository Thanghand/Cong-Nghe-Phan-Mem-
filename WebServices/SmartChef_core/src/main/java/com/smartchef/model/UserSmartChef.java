package com.smartchef.model;

import java.util.ArrayList;

public class UserSmartChef {

	private String profilePicture;
	private String fullName;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private int priID; // Permission User
	private ArrayList<String> favoriteMeal = new ArrayList<String>();

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPriID() {
		return priID;
	}

	public void setPriID(int priID) {
		this.priID = priID;
	}
	
	public ArrayList<String> getFavoriteMeal() {
		return favoriteMeal;
	}

	public void setFavoriteMeal(ArrayList<String> favoriteMeal) {
		this.favoriteMeal = favoriteMeal;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
