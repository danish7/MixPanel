package com.example.danisharshad.mixpanelproject.ui.activities;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.danisharshad.mixpanelproject.R;
import com.example.danisharshad.mixpanelproject.loaders.ProfileLoader;
import com.example.danisharshad.mixpanelproject.adapters.ProfileAdapter;
import com.example.danisharshad.mixpanelproject.model.Profile;

import java.util.List;

// TODO: handle internet connectivity changes, register a local broadcast receiver to make api call again

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Profile>> {

    private static final int LOADER_ID = 1;
    private ProfileAdapter mAdapter;
    private IntentFilter intentFilter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle args = new Bundle();
        ListView mProfileList = (ListView) findViewById(R.id.profile_list);
        mAdapter = new ProfileAdapter();
        mProfileList.setAdapter(mAdapter);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_profiles));
        getLoaderManager().restartLoader(LOADER_ID, args, MainActivity.this);
        mProgressDialog.show();

    }

    @Override
    public Loader<List<Profile>> onCreateLoader(int id, Bundle args) {
        return new ProfileLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<List<Profile>> loader, List<Profile> data) {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (data != null) {
            mAdapter.setProfileList(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Profile>> loader) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProgressDialog.dismiss();

    }
}
