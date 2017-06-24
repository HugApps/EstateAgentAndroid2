package com.example.hugo.estatefinder.API;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hugo.estatefinder.LoginActivity;
import com.example.hugo.estatefinder.WarningDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hugo on 2017-04-03.
 */
// Handles API connections

public class  apiCaller  {
    private RequestQueue mRequestQueue;
    private static apiCaller instance;
    private Context mContext;
    String baseUrl = "http://10.0.2.2:3000/api/";
    JSONObject responseData;

    private apiCaller (Context context ) {

        this.mContext = context;
        this.mRequestQueue = getRequestQueue();

    }


    public static synchronized apiCaller getInstance(Context context) {
        if (instance == null) {
            instance = new apiCaller (context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }


    public void makePostRequest (String api_call , JSONObject params , final Apicallback callback) {

        String requestURL = baseUrl+api_call;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, requestURL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseData = response;
                callback.getApiResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = "{message: 'Login Failed'}";
                try {
                   // System.out.println(error.networkResponse.data.toString());
                    JSONObject errorJSON = new JSONObject(message);
                    callback.getApiError(errorJSON);
                } catch ( JSONException je) {
                    je.printStackTrace();
                }
            }
        }

        );
        getRequestQueue().add(request);
    }

    public void makeGetRequest (String api_call, JSONObject params , final Apicallback callback) {
        String url = baseUrl + api_call;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url,params,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.getApiResult(response);
            }
          },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = "{message: 'error, something went wrong'}";
                try {
                    // System.out.println(error.networkResponse.data.toString());
                    JSONObject errorJSON = new JSONObject(message);
                    callback.getApiError(errorJSON);
                } catch ( JSONException je) {
                    je.printStackTrace();
                }
            }
        }

        );
        getRequestQueue().add(getRequest);
    }



}
