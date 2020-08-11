
package com.fund.flio.data;


import com.fund.flio.data.local.prefs.PreferencesHelper;
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
    public String getAuthType() {
        return mPreferences.getAuthType();
    }

    @Override
    public void setAuthType(String authType) {
        mPreferences.setAuthType(authType);
    }

    @Override
    public Single<Response<Void>> getTestSelect() {
        return mFlioApi.getTestSelect();
    }

    @Override
    public Single<Response<Void>> postAuthToken(TokenBody tokenBody) {
        return mAuthApi.postAuthToken(tokenBody);
    }
}
