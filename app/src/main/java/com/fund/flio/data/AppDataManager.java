
package com.fund.flio.data;


import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.TokenBody;
import com.fund.flio.data.remote.ApiHelper;
import com.fund.flio.di.provider.SchedulerProvider;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
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
    public String getFirebaseToken() {
        return mPreferences.getFirebaseToken();
    }

    @Override
    public void setFirebaseToken(String firebaseToken) {
        mPreferences.setFirebaseToken(firebaseToken);
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
