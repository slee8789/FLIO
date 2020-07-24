package com.fund.flio.di.module;

import android.content.Context;

import com.fund.flio.BuildConfig;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.nhn.android.naverlogin.OAuthLogin;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    @Provides
    FirebaseAuth provideFirebaseOAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    GoogleSignInOptions provideGSO() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(BuildConfig.GOOGLE_SIGN_URL)
//                .requestServerAuthCode(serverClientId)
                .requestEmail()
                .build();
    }

    @Provides
    GoogleSignInClient provideGoogleOAuth(Context context, GoogleSignInOptions gso) {
        return GoogleSignIn.getClient(context, gso);
    }

    @Provides
    OAuthLogin provideNaverOAuth(Context context) {
        OAuthLogin mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(context, BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET, BuildConfig.NAVER_CLIENT_NAME);
        return mOAuthLoginModule;
    }

}
