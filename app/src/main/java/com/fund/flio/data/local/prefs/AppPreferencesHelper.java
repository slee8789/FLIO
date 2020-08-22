package com.fund.flio.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;

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
    public String getFirebaseToken() {
        return mPrefs.getString(context.getString(R.string.key_firebase_token), null);
    }

    @Override
    public void setFirebaseToken(String fcmToken) {
        mPrefs.edit().putString(context.getString(R.string.key_firebase_token), fcmToken).apply();
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
