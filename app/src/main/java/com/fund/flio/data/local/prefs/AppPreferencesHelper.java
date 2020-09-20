package com.fund.flio.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.fund.flio.R;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.di.qualifier.PreferenceInfo;

import javax.inject.Inject;


public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;
    private final Context context;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.context = context;
    }

    @Override
    public String getUserId() {
        return mPrefs.getString(context.getString(R.string.key_user_id), null);
    }

    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(context.getString(R.string.key_user_id), userId).apply();
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(context.getString(R.string.key_user_name), null);
    }

    @Override
    public void setUserName(String userName) {
        mPrefs.edit().putString(context.getString(R.string.key_user_name), userName).apply();
    }

    @Override
    public String getUserImageUrl() {
        return mPrefs.getString(context.getString(R.string.key_user_image_url), null);
    }

    @Override
    public void setUserImageUrl(String userImageUrl) {
        mPrefs.edit().putString(context.getString(R.string.key_user_image_url), userImageUrl).apply();
    }

    @Override
    public String getUserToken() {
        return mPrefs.getString(context.getString(R.string.key_user_token), null);
    }

    @Override
    public void setUserToken(String fcmToken) {
        mPrefs.edit().putString(context.getString(R.string.key_user_token), fcmToken).apply();
    }

    @Override
    public String getMessageToken() {
        return mPrefs.getString(context.getString(R.string.key_message_token), null);
    }

    @Override
    public void setMessageToken(String messageToken) {
        mPrefs.edit().putString(context.getString(R.string.key_message_token), messageToken).apply();
    }

    @Override
    public String getAuthType() {
        return mPrefs.getString(context.getString(R.string.key_auth_type), AuthType.NONE.getType());
    }

    @Override
    public void setAuthType(String authType) {
        mPrefs.edit().putString(context.getString(R.string.key_auth_type), authType).apply();
    }
}
