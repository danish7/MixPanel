package com.example.danisharshad.mixpanelproject.model;

import com.example.danisharshad.mixpanelproject.Utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by danisharshad on 5/17/16.
 */
public class Properties {

    public static final String COUNTRY_CODE_TAG = "$country_code";
    public static final String CITY_TAG         = "$city";
    public static final String CREATED_TAG      = "$created";
    public static final String EMAIL_TAG        = "$email";
    public static final String NAME_TAG         = "$name";
    public static final String PHONE_TAG        = "phone";
    public static final String PHOTO_URL_TAG    = "photo_url";
    public static final String REGION_TAG       = "$region";

    private String city;
    private String countryCode;
    private String createdAt;
    private String email;
    private String name;
    private String region;
    private String phone;
    private String photoUrl;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static Properties parseProperties (JSONObject jsonObject) throws JSONException {
        Properties result = new Properties();
        result.setCountryCode(JsonUtils.getStringValue(jsonObject, COUNTRY_CODE_TAG));
        result.setCity(JsonUtils.getStringValue(jsonObject, CITY_TAG));
        result.setCreatedAt(JsonUtils.getStringValue(jsonObject, CREATED_TAG));
        result.setEmail(JsonUtils.getStringValue(jsonObject,EMAIL_TAG));
        result.setName(JsonUtils.getStringValue(jsonObject, NAME_TAG));
        result.setPhone(JsonUtils.getStringValue(jsonObject, PHONE_TAG));
        result.setPhotoUrl(JsonUtils.getStringValue(jsonObject, PHOTO_URL_TAG));
        result.setRegion(JsonUtils.getStringValue(jsonObject, REGION_TAG));
        return result;
    }
}
