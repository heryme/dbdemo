package com.jsonparserdemo.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Rid's Patel on 19-04-2018.
 */

public abstract class APIService {

    private static final String TAG = APIService.class.getSimpleName();

    public static final String BASE_URL = "http://polls.apiblueprint.org/questions";

    public interface Success<T> {
        public void onSuccess(T response);
    }

    public interface Error<T>
    {
        public void onError(T error);
    }

    protected static void handleError(final Context context, VolleyError error) {
        Log.d(TAG, "Error :: " + error);

        if (error instanceof NoConnectionError) {

            Toast.makeText(context, "Please check internet connection !", Toast.LENGTH_LONG).show();

        } else if (error.networkResponse != null) {

            Log.d(TAG, "CODE =" + error.networkResponse.statusCode);
            Log.d(TAG, new String(error.networkResponse.data));

            if (error.networkResponse.statusCode == 401) {
                Toast.makeText(context,"Error 401",Toast.LENGTH_LONG).show();

            } else {
                //Read Error Response and Display Error Dialog                //DialogUtility.(context, getErrorMessage(error.networkResponse.data));                Toast.makeText(context,getErrorMessage(error.networkResponse.data),Toast.LENGTH_LONG).show();
            }
        }else{
            Log.d(TAG,"Server is not responding");
            Toast.makeText(context,"Server is not responding",Toast.LENGTH_LONG).show();
        }
    }

    private static String getErrorMessage(byte[] responseData) {
        String message = null;
        String code = null;
        try {
            JSONObject jsonObject = new JSONObject(new String(responseData));
            JSONObject subJsonObj = jsonObject.getJSONObject("error");
            code = subJsonObj.getString("code");
            message = subJsonObj.getString("message");
        } catch (Exception e) {
            Log.d(TAG, "getErrorMessage" + e.getMessage());
            message = "Something is wrong,Please try again";
        }
        return message;
    }

}