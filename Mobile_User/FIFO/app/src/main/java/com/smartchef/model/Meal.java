package com.smartchef.model;

import java.io.Serializable;

/**
 * Created by Administrator on 19-May-15.
 */
public class Meal implements Serializable {
    private int nationID;
    private String mealNameVN;
    private int mealTypeID;
    private int timeID;
    private int festivalID;
    private String mealPicture;
    private String descriptionVN;
    private String tutorialVN;
    private String insertedByEmail;
    private int numberOfLike;
    private int mealID;

    // Constructor
    public Meal() {
    }

    public Meal(String mealName, String mealPicture, int numberOfLike) {
        this.mealNameVN = mealName;
        this.mealPicture = mealPicture;
        this.numberOfLike = numberOfLike;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public int getNumberOfLike() {
        return numberOfLike;
    }

    public void setNumberOfLike(int numberOfLike) {
        this.numberOfLike = numberOfLike;
    }

    public int getNationID() {
        return nationID;
    }

    public void setNationID(int nationID) {
        this.nationID = nationID;
    }

    public String getMealName() {
        return mealNameVN;
    }

    public void setMealName(String mealName) {
        this.mealNameVN = mealName;
    }

    public int getMealTypeID() {
        return mealTypeID;
    }

    public void setMealTypeID(int mealTypeID) {
        this.mealTypeID = mealTypeID;
    }

    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public int getFestivalID() {
        return festivalID;
    }

    public void setFestivalID(int festivalID) {
        this.festivalID = festivalID;
    }

    public String getMealPicture() {
        return mealPicture;
    }

    public void setMealPicture(String mealPicture) {
        this.mealPicture = mealPicture;
    }

    public String getdescriptionVN() {
        return descriptionVN;
    }

    public void setDescriptionVN(String descriptionVN) {
        this.descriptionVN = descriptionVN;
    }

    public String getTutorialVN() {
        return tutorialVN;
    }

    public void setTutorialVN(String tutorialVN) {
        this.tutorialVN = tutorialVN;
    }

    public String getInsertedByEmail() {
        return insertedByEmail;
    }

    public void setInsertedByEmail(String insertedByEmail) {
        this.insertedByEmail = insertedByEmail;
    }

}
