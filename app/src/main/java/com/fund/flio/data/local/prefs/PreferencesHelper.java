package com.fund.flio.data.local.prefs;

public interface PreferencesHelper {

    String getFirebaseToken();

    void setFirebaseToken(String firebaseToken);

    String getAuthType();

    void setAuthType(String authType);

}
