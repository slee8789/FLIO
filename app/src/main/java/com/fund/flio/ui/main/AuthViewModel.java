package com.fund.flio.ui.main;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.TokenBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.orhanobut.logger.Logger;

import io.reactivex.rxjava3.core.Single;


public class AuthViewModel extends BaseViewModel implements ISessionCallback {

    private OAuthLogin mOAuthLoginModule;

    private FirebaseAuth mFirebaseAuth;
    private GoogleApiClient googleApiClient;

    private Context mContext;

    private MutableLiveData<AuthenticationState> authenticationState = new MutableLiveData<>();

    public MutableLiveData<AuthenticationState> getAuthenticationState() {
        return authenticationState;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public AuthViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider, OAuthLogin oAuthLogin) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("AuthViewModel constructor");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mOAuthLoginModule = oAuthLogin;
        mContext = context;
        Session.getCurrentSession().addCallback(this);
        authenticationState.setValue(AuthenticationState.NONE);
    }

    public void postAuthToken(AuthType authType, String authToken) {
        Logger.d("postAuthToken");
        getCompositeDisposable().add(getDataManager().postAuthToken(new TokenBody(authType.getType(), authToken, getDataManager().getMessageToken()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().io())
                .subscribe(userToken -> {
                    Logger.d("postAuthToken result " + userToken.body().getUserToken());
                    firebaseLogin(authType, userToken.body().getUserToken());
                }, onError -> {
                    Logger.e("postAuthToken error " + onError);
                }));
    }

    public void firebaseLogin(AuthType authType, String userToken) {
        Logger.d("firebaseLogin authType " + authType + ", firebaseToken " + userToken);
        mFirebaseAuth.signInWithCustomToken(userToken).addOnCompleteListener(task -> {
            Logger.i("Firebase Auth Success " + mFirebaseAuth.getCurrentUser());
            getDataManager().setAuthType(authType.getType());
            getDataManager().setUserToken(userToken);
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
        });
    }

    @Override
    public void onSessionOpened() {
        Logger.d("kakao onSessionOpened result " + Session.getCurrentSession().getTokenInfo());
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Logger.e("onSessionClosed " + errorResult);
            }

            @Override
            public void onSuccess(MeV2Response result) {
                if (result.getKakaoAccount().emailNeedsAgreement().getBoolean()) {
                    authenticationState.setValue(AuthenticationState.KAKAO_EMAIL_NEED_AGREE);
                } else {
                    postAuthToken(AuthType.KAKAO, Session.getCurrentSession().getTokenInfo().getAccessToken());
                }
            }
        });
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Logger.e("onSessionOpenFailed " + exception);
        setIsLoading(false);
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Logger.d("firebaseAuthWithGoogle before " + acct.getIdToken() + ", uid " + mFirebaseAuth.getUid());
//        Logger.d("firebaseAuthWithGoogle uid " + mFirebaseAuth.getCurrentUser().getUid());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Logger.d("firebaseAuthWithGoogle success ");
                        //Todo : uid 값 string으로 대체
                        getCompositeDisposable().add(getDataManager().postInsertUser(new User(mFirebaseAuth.getCurrentUser().getUid(), acct.getEmail(), acct.getDisplayName(), acct.getPhotoUrl().toString(),null, acct.getIdToken(), getDataManager().getMessageToken()))
                                .observeOn(getSchedulerProvider().ui())
                                .subscribeOn(getSchedulerProvider().io())
                                .subscribe(Void -> {
                                    getDataManager().setAuthType(AuthType.GOOGLE.getType());
                                    getDataManager().setUserToken(acct.getIdToken());
                                    authenticationState.setValue(AuthenticationState.AUTHENTICATED);
                                }));
                    } else {
                        Logger.e("google login error " + task.toString());
                    }
                });
    }

    public void logout() {
        Logger.d("logout " + getDataManager().getAuthType() + ", mOAuthLoginModule " + mOAuthLoginModule);
        switch (AuthType.valueOf(getDataManager().getAuthType())) {
            case KAKAO:
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Logger.d("kakao logout onComplete Logout");
                    }
                });
                break;

            case NAVER:
                getCompositeDisposable().add(Single.create(subscriber -> mOAuthLoginModule.logoutAndDeleteToken(mContext))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().io())
                        .subscribe(result -> Logger.d("naver logout onComplete " + mOAuthLoginModule.getState(mContext))));
                break;

            case GOOGLE:
                Logger.d("google logout before " + googleApiClient);
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(status -> {
                    if (status.isSuccess()) {
                        Logger.d("google logout onComplete");
                    }
                });
                break;
        }
        mFirebaseAuth.signOut();
        setIsLoading(false);
        getDataManager().setAuthType(AuthType.NONE.getType());
        getDataManager().setUserToken(null);
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.i("AuthViewModel onCleared");
    }
}
