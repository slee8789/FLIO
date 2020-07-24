package com.fund.flio.ui.main.login;


import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel<LoginNavigator> implements ISessionCallback {

    @Inject
    FirebaseAuth mFirebaseAuth;

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Session.getCurrentSession().addCallback(this);
    }

    @Override
    public void onSessionOpened() {
        Logger.d("kakao onSessionOpened result " + Session.getCurrentSession().getTokenInfo());
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                getNavigator().handleError(errorResult.getException());
            }

            @Override
            public void onSuccess(MeV2Response result) {

//                if (result.getKakaoAccount().emailNeedsAgreement().getBoolean()) {
//                    getNavigator().showKakaoAuthPopup();
//                } else {
//                    getCompositeDisposable().add(getDataManager().postLogin(new User(result.getKakaoAccount().getEmail(), result.getKakaoAccount().getProfile().getNickname(), getDataManager().getPushToken(), result.getKakaoAccount().getProfile().getThumbnailImageUrl(), SNSType.KAKAO.getSnsType()))
//                            .doOnSuccess(userInfo -> getDataManager().setMyInfo(userInfo.body().get(0)))
//                            .flatMap(userInfo -> getDataManager().postPortfolios(userInfo.body().get(0).getId()))
//                            .observeOn(getSchedulerProvider().ui())
//                            .subscribeOn(getSchedulerProvider().io())
//                            .subscribe(portFolio -> {
//                                if (portFolio.isSuccessful()) {
//                                    getDataManager().setMyPortfolios(portFolio.body());
//                                    getNavigator().startMainActivity();
//                                } else {
//                                    Logger.e("Login Error");
//                                }
//
//                            }, onError -> getNavigator().handleError(onError)));
//                }
// 필요한 동의항목의 scope ID (개발자사이트 해당 동의항목 설정에서 확인 가능)


// 사용자 동의 요청


//                UserAccount kakaoAccount = result.getKakaoAccount();
//                if (kakaoAccount != null) {
//
//                    // 이메일
//                    String email = kakaoAccount.getEmail();
//
//                    if (email != null) {
//                        Log.i("KAKAO_API", "email: " + email);
//
//                    } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
//                        // 동의 요청 후 이메일 획득 가능
//                        // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
//
//                    } else {
//                        // 이메일 획득 불가
//                    }
//
//                    // 프로필
//                    Profile profile = kakaoAccount.getProfile();
//
//                    if (profile != null) {
//                        Log.d("KAKAO_API", "nickname: " + profile.getNickname());
//                        Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
//                        Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());
//
//                    } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
//                        // 동의 요청 후 프로필 정보 획득 가능
//
//                    } else {
//                        // 프로필 획득 불가
//                    }
//                }


//                getCompositeDisposable().add(getDataManager().postLogin(new User("test@daum.net"/*result.getKakaoAccount().getEmail()*/, result.getKakaoAccount().getProfile().getNickname(), getDataManager().getPushToken(), result.getKakaoAccount().getProfile().getThumbnailImageUrl(), SNSType.KAKAO.getSnsType()))
//                        .doOnSuccess(userInfo -> getDataManager().setMyInfo(userInfo.body().get(0)))
//                        .flatMap(userInfo -> getDataManager().postPortfolios(userInfo.body().get(0).getId()))
//                        .observeOn(getSchedulerProvider().ui())
//                        .subscribeOn(getSchedulerProvider().io())
//                        .subscribe(portFolio -> {
//                            Logger.d("portFolio " + portFolio.isSuccessful());
//                            if (portFolio.isSuccessful()) {
//                                getDataManager().setMyPortfolios(portFolio.body());
//                                getNavigator().startMainActivity();
//                            } else {
//                                Logger.e("Login Error");
//                            }
//
//                        }, onError -> getNavigator().handleError(onError)));

            }
        });
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Logger.e("onSessionOpenFailed " + exception);
    }
}
