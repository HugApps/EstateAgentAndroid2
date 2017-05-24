package com.example.hugo.estatefinder.API;

import org.json.JSONObject;

/**
 * Created by Hugo on 2017-05-21.
 */

public interface Apicallback {

    JSONObject getApiResult (JSONObject response);
    String getApiError(JSONObject response);

}
