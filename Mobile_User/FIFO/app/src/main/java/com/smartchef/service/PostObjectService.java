package com.smartchef.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.smartchef.controller.BaseFragmentActivity;
import com.smartchef.fragments.BaseFragment;
import com.smartchef.utils.LoadContant;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 05-May-15.
 */
public class PostObjectService extends AsyncTask<String, Void, Void> implements ActivityDataService {
    private boolean createCheckPost = false;
    private String urlWebSerivces = "";
    private String feedBack = "";
    private BaseFragmentActivity activity;
    private FeedbackActivityService feedBackActivityService;
    private String result = "";

    /**
     * Creates a new asynchronous task. This constructor must be invoked on the UI thread.
     */
    public PostObjectService() {
        super();
    }

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param arg The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Void doInBackground(String... arg) {
        String data = arg[0];

        // Preparing post params
        ServiceHandler serviceClient = new ServiceHandler();

        String json = serviceClient.makeServiceCall(getUrlWebServices(),
                ServiceHandler.POST, data);

        Log.d("Create Response: ", "> " + json);
        if (json != null) {
            json = json.trim();
            if (json.equals(LoadContant.ERROR)) {
                createCheckPost = false;
            } else {
                createCheckPost = true;
            }
            feedBack = json;

//            if (json.equals(LoadContant.SUCCESSFULL)) {
//                createCheckPost = true;
//            } else if (json.equals(LoadContant.ERROR)) {
//                createCheckPost = false;
//            }

        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }

        return null;
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p/>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aVoid The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("PostObjectService", "FeedBack :" + feedBack);
        feedBackActivityService.getFeedBack(feedBack);
    }

    /**
     * Getter and Setter
     */
    public void setUrlWebServices(String url) {
        this.urlWebSerivces = url;
    }

    public String getUrlWebServices() {
        return this.urlWebSerivces;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setActivity(BaseFragmentActivity activity) {
        this.activity = activity;
    }

    public BaseFragmentActivity getActivity() {
        return this.activity;
    }

    @Override
    public void setFeedBackActivityService(FeedbackActivityService feedBackActivityService) {
        this.feedBackActivityService = feedBackActivityService;
    }

    @Override
    public FeedbackActivityService getFeedBackActivityService() {
        return feedBackActivityService;
    }
}
