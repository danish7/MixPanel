package com.example.danisharshad.mixpanelproject.model;

import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danisharshad on 5/17/16.
 */
public class Profile {

    public static final String DISTINCT_ID_TAG = "$distinct_id";
    public static final String PROPERTIES_TAG  = "$properties";

    private String distinctId;
    private Properties properties;


    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static List<Profile> parseProfiles (JSONArray jsonArray) throws JSONException {
        List<Profile> profiles = new ArrayList<>();
        for (int x = 0; x < jsonArray.length(); x++) {
            JSONObject profile = jsonArray.getJSONObject(x);
            if (profile.has(PROPERTIES_TAG)) {
                Profile userProfile = new Profile();
                JSONObject properties = profile.getJSONObject(PROPERTIES_TAG);
                userProfile.setDistinctId(profile.getString(DISTINCT_ID_TAG));
                Properties profileProperties = Properties.parseProperties(properties);
                userProfile.setProperties(profileProperties);
                profiles.add(userProfile);
            }
        }
        return profiles;
    }
}
