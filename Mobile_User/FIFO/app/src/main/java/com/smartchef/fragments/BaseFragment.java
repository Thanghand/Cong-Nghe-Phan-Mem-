package com.smartchef.fragments;

import android.support.v4.app.Fragment;

import com.smartchef.service.ActivityDataService;
import com.smartchef.service.FeedbackActivityService;

/**
 * Created by Administrator on 07-May-15.
 */
public class BaseFragment extends Fragment implements ActivityDataService {

    private  FeedbackActivityService feedBackActivityService;

    @Override
    public void setFeedBackActivityService(FeedbackActivityService feedBackActivityService) {
        this.feedBackActivityService = feedBackActivityService;
    }

    @Override
    public FeedbackActivityService getFeedBackActivityService() {
        return feedBackActivityService;
    }
}
