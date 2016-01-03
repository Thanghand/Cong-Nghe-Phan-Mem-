package com.smartchef.service;

import com.smartchef.model.UserSmartChef;

/**
 * Created by Administrator on 10-May-15.
 */
public interface FeedbackActivityService {
    /**
     * @param feedBack
     */
    void getFeedBack(String feedBack);

    /**
     * @return
     */
    UserSmartChef getUserSmartChef();

    /**
     * @param userSmartChef
     */
    void setUserSmartChef(UserSmartChef userSmartChef);

    /**
     * @param flagUser
     */
    void setFlagUser(String flagUser);

    /**
     * @return
     */
    String getFlagUser();
}
