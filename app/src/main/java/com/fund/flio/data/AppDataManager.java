
package com.fund.flio.data;


import com.fund.flio.data.local.prefs.PreferencesHelper;
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

    @Inject
    public AppDataManager(PreferencesHelper preferences, @Named("flio") ApiHelper flioApi) {
        mPreferences = preferences;
        mFlioApi = flioApi;
    }

    @Override
    public Single<Response<Void>> getTestSelect() {
        return mFlioApi.getTestSelect();
    }
}
