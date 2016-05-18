package com.example.danisharshad.mixpanelproject.adapters;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danisharshad.mixpanelproject.R;
import com.example.danisharshad.mixpanelproject.model.Profile;
import com.example.danisharshad.mixpanelproject.model.Properties;
import com.example.danisharshad.mixpanelproject.tasks.ImageDownloaderTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danisharshad on 5/17/16.
 */
public class ProfileAdapter extends BaseAdapter {

    private static final String TAG = "ProfileAdapter";

    private List<Profile> mProfileList;

    private static class ViewHolder {
        ImageView profileImage;
        TextView name;
        TextView phone;
        TextView email;
        TextView location;
    }

    public ProfileAdapter () {
        mProfileList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mProfileList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProfileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setProfileList (List<Profile> profileList) {
        this.mProfileList = profileList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.list_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.email = (TextView) convertView.findViewById(R.id.emailValue);
            viewHolder.location = (TextView) convertView.findViewById(R.id.locationValue);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.phoneValue);
            viewHolder.profileImage = (ImageView) convertView.findViewById(R.id.profile_image);
            convertView.setTag(viewHolder);
        } {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Profile profile = (Profile) getItem(position);
        Properties profileProperties = profile.getProperties();
        if (profileProperties != null) {
            viewHolder.name.setText(profileProperties.getName());
            viewHolder.location.setText(profileProperties.getCity());
            viewHolder.email.setText(profileProperties.getEmail());
            viewHolder.phone.setText(profileProperties.getPhone());
            viewHolder.profileImage.setTag(mProfileList.get(position));
            if (profileProperties.getPhotoUrl() != null) {
                Picasso.with(viewHolder.profileImage.getContext()).load(profileProperties.getPhotoUrl())
                        .placeholder(R.drawable.ic_launcher)
                        .error(R.drawable.ic_launcher)
                        .into(viewHolder.profileImage);
            }
        }
        return convertView;
    }
}
