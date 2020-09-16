
package com.fund.flio.data;


import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.ChatInsertBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TokenBody;
import com.fund.flio.data.remote.ApiHelper;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;


@Singleton
public class AppDataManager implements DataManager {


    private final PreferencesHelper mPreferences;
    private final ApiHelper mFlioApi;
    private final ApiHelper mAuthApi;

    @Inject
    public AppDataManager(PreferencesHelper preferences, @Named("flio") ApiHelper flioApi, @Named("auth") ApiHelper authApi) {
        mPreferences = preferences;
        mFlioApi = flioApi;
        mAuthApi = authApi;
    }

    @Override
    public Single<Response<User>> postAuthToken(TokenBody tokenBody) {
        return mAuthApi.postAuthToken(tokenBody);
    }

    @Override
    public Single<Response<Void>> postInsertUser(User user) {
        return mFlioApi.postInsertUser(user);
    }

    @Override
    public Single<Response<Void>> insertMyChatDetail(ChatInsertBody chatInsertBody) {
        return mFlioApi.insertMyChatDetail(chatInsertBody);
    }

    @Override
    public Single<Response<ChatRoomWrapper>> selectMyChat(ChatListBody chatListBody) {
        return mFlioApi.selectMyChat(chatListBody);
    }

    @Override
    public Single<Response<MessageWrapper>> selectMyChatDetail(ChatDetailBody chatDetailBody) {
        return mFlioApi.selectMyChatDetail(chatDetailBody);
    }

    @Override
    public String getUserToken() {
        return mPreferences.getUserToken();
    }

    @Override
    public void setUserToken(String firebaseToken) {
        mPreferences.setUserToken(firebaseToken);
    }

    @Override
    public String getMessageToken() {
        return mPreferences.getMessageToken();
    }

    @Override
    public void setMessageToken(String messageToken) {
        mPreferences.setMessageToken(messageToken);
    }

    @Override
    public String getAuthType() {
        return mPreferences.getAuthType();
    }

    @Override
    public void setAuthType(String authType) {
        mPreferences.setAuthType(authType);
    }
}
