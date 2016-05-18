package com.example.danisharshad.mixpanelproject.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by danisharshad on 5/17/16.
 */
public class JsonUtils {

    public static String getStringValue (JSONObject jsonObject, String jsonTag) throws JSONException {
        String result = null;
        if (jsonObject.has(jsonTag)) {
            return  jsonObject.getString(jsonTag);
        }
        return result;
    }
}
