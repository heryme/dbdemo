package com.jsonparserdemo.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rid's Patel on 19-04-2018.
 */

public class DemoGsonDataService extends APIService {

    private static final String TAG = DemoGsonDataService.class.getSimpleName();
    private static final String URL_LOGIN = BASE_URL;


    public static void DemoGsonDataService(final Context context,/* final HashMap<String, String> params,*/
                                    final Success<JSONObject> successListener) {

       // final ProgressDialog dialog = DialogUtils.showProgress(context);

        Log.d(TAG, "OSC Login URL-->" + URL_LOGIN);
        StringRequest overviewRequest = new StringRequest(Request.Method.GET, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ///dialog.hide();
                    successListener.onSuccess(new JSONObject(response));
                    Log.d(TAG, "OSC Login Response" + response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // dialog.hide();
                try {
                    Log.d(TAG, "OSC Login Error  >>" + error.toString());
                    //handleError(context,error);
                } catch (Exception e) {
                    Log.d(TAG, "" + e.getMessage());
                }

            }
        });/* {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };*/

        APIController.getInstance(context).addRequest(overviewRequest, TAG);

    }
}
