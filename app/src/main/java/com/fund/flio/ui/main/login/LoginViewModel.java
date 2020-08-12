package com.fund.flio.ui.main.login;


import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.data.model.body.TokenBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginViewModel extends BaseViewModel implements ISessionCallback {

    @Inject
    FirebaseAuth mFirebaseAuth;

    public ObservableField<String> token = new ObservableField<>();
    private MutableLiveData<AuthenticationState> authenticationState = new MutableLiveData<>();

    public MutableLiveData<AuthenticationState> getAuthenticationState() {
        return authenticationState;
    }

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("LoginViewModel constructor");
        Session.getCurrentSession().addCallback(this);
    }

    public void dummyLoading() {
        getCompositeDisposable().add(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
                }));
    }

    public void authenticate(boolean cool) {
        Logger.d("authenticate " + cool);
        if (cool) {
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
        } else {
            authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        }
    }

    @Override
    public void onSessionOpened() {
        Logger.d("kakao onSessionOpened result " + Session.getCurrentSession().getTokenInfo());
        token.set(Session.getCurrentSession().getTokenInfo().getAccessToken());
        getCompositeDisposable().add(getDataManager().postAuthToken(new TokenBody(AuthType.KAKAO.getType(), Session.getCurrentSession().getTokenInfo().getAccessToken())).subscribe(firebaseToken -> {
            getDataManager().setAuthType(AuthType.KAKAO.getType());
        }, onError -> {
//            getNavigator().handleError(onError);
        }));
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Logger.e("onSessionOpenFailed " + exception);
        getCompositeDisposable().add(Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
                }));
    }
}
