package com.fund.flio.data.local.prefs;

public interface PreferencesHelper {

    String getUserToken();

    void setUserToken(String userToken);

    String getMessageToken();

    void setMessageToken(String messageToken);

    String getAuthType();

    void setAuthType(String authType);

}
