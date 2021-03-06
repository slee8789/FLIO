package com.fund.flio.data.local.prefs;

public interface PreferencesHelper {

    String getUserId();

    void setUserId(String userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String userEmail);

    String getUserImageUrl();

    void setUserImageUrl(String userImageUrl);

    String getUserToken();

    void setUserToken(String userToken);

    String getMessageToken();

    void setMessageToken(String messageToken);

    String getAuthType();

    void setAuthType(String authType);

    boolean notifyChat();

    void setNotifyChat(boolean chat);

    boolean notifyEvery();

    void setNotifyEvery(boolean all);

}
