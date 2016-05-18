package com.example.danisharshad.mixpanelproject.loaders;

/**
 * Created by danisharshad on 5/17/16.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.example.danisharshad.mixpanelproject.api.MixPanelApi;
import com.example.danisharshad.mixpanelproject.model.Profile;

import org.json.JSONArray;

import java.util.List;

public class ProfileLoader extends AsyncTaskLoader<List<Profile>> {

    private static final String TAG = "ProfileLoader";

    private List<Profile> result;

    public ProfileLoader(Context context, Bundle args) {
        super(context);
    }

    @Override
    public List<Profile> loadInBackground() {
        List<Profile> profileList = null;
        try {
            JSONArray jsonArray =  MixPanelApi.fetchEngageResults();
            profileList = Profile.parseProfiles(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileList;
    }


    @Override
    public void deliverResult(List<Profile> data) {
        super.deliverResult(data);
        if (isReset()) {
            return;
        }
        result = data;
        super.deliverResult(data);
    }



    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            deliverResult(result);
        }

        if (takeContentChanged() || result == null) {
            forceLoad();
        }
    }


    @Override
    protected void onReset() {
        // Ensure the loader has been stopped.
        onStopLoading();
        result= null;
    }

    @Override
    public void onCanceled(List<Profile> data) {
        super.onCanceled(data);
    }





}

