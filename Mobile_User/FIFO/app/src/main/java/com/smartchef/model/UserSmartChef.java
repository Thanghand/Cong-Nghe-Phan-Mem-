package com.smartchef.model;


import android.graphics.Bitmap;

import com.smartchef.utils.JsonUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 05-May-15.
 */
public class UserSmartChef implements Serializable {
    private ArrayList<String> pictureProfile = new ArrayList<>();
    private String profilePicture;
    private String fullName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int priID;  // Permission User
    private boolean active;  //  Confirm Email
    private ArrayList<String> favoriteMeal = new ArrayList<>();
    private String flagUser;

    public String getFlagUser() {
        return flagUser;
    }

    public void setFlagUser(String flagUser) {
        this.flagUser = flagUser;
    }

    public String getProfilePicture() {
        profilePicture = JsonUtil.convertObjectToJson(pictureProfile);
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCurrentProfile(int pos) {
        return pictureProfile.get(pos);
    }

    public void addNewImageProfile(String newProFile) {
        pictureProfile.add(newProFile);
        profilePicture = JsonUtil.convertObjectToJson(pictureProfile);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<String> getFavoriteMeal() {
        return favoriteMeal;
    }

    public void setFavoriteMeal(ArrayList<String> favoriteMeal) {
        this.favoriteMeal = favoriteMeal;
    }


    public ArrayList<String> getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(ArrayList<String> pictureProfile) {
        this.pictureProfile = pictureProfile;
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
