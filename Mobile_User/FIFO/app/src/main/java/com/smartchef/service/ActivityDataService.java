package com.smartchef.service;

import com.smartchef.controller.BaseActionBarActivity;
import com.smartchef.controller.BaseFragmentActivity;

/**
 * Created by Administrator on 02-Jun-15.
 */
public interface ActivityDataService {
    //    /**
//     * @param baseFragmentActivity
//     */
//    void setBaseFragmentActivity(BaseFragmentActivity baseFragmentActivity);
//
//    /**
//     * @return
//     */
//    BaseFragmentActivity getBaseFragmentActivity();
//
//    /**
//     * @param baseActionBarActivity
//     */
//    void setBaseActionBarActivity(BaseActionBarActivity baseActionBarActivity);
//
//    /**
//     * @return
//     */
//    BaseActionBarActivity getBaseActionBarActivity();\

    /**
     * @param feedBackActivityService
     */
    void setFeedBackActivityService(FeedbackActivityService feedBackActivityService);

    /**
     * @return
     */
    FeedbackActivityService getFeedBackActivityService();
}
