package com.fund.flio.ui.main.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.databinding.FragmentLoginBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.home.HomeFragmentDirections;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.kakao.auth.AccessTokenCallback;
import com.kakao.auth.Session;
import com.kakao.auth.StringSet;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;


public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> {

    @Inject
    OAuthLogin mOAuthLoginModule;

    @Inject
    GoogleSignInClient mGoogleSignInClient;

    private OAuthLoginButton mOAuthLoginButton;

    private AuthViewModel authViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return getViewModelProvider().get(LoginViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("LoginFragment onCreate");
//        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = ((MainActivity) getBaseActivity()).getAuthViewModel();
        getViewDataBinding().setAuthViewModel(authViewModel);
        initViews();
    }

    private void initViews() {
        mOAuthLoginButton = getViewDataBinding().btnNaver;
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        getViewDataBinding().btnKakaoCustom.setOnClickListener(view -> {
            authViewModel.setIsLoading(true);
            getViewDataBinding().btnKakao.performClick();
        });

        getViewDataBinding().btnNaverCustom.setOnClickListener(view -> {
            authViewModel.setIsLoading(true);
            getViewDataBinding().btnNaver.performClick();
        });
        getViewDataBinding().btnGoogleCustom.setOnClickListener(view -> {
            authViewModel.setIsLoading(true);
            getBaseActivity().startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_GOOGLE_SIGN_IN);
        });
    }
    @SuppressLint("HandlerLeak")
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(getBaseActivity());
                String refreshToken = mOAuthLoginModule.getRefreshToken(getBaseActivity());
                @SuppressLint("HandlerLeak") long expiresAt = mOAuthLoginModule.getExpiresAt(getBaseActivity());
                String tokenType = mOAuthLoginModule.getTokenType(getBaseActivity());
                Logger.d("mOAuthLoginHandler " + success + ", accessToken " + accessToken + ", refreshToken " + refreshToken + ", expiresAt " + expiresAt + ", tokenType " + tokenType);
                ((MainActivity) getBaseActivity()).getAuthViewModel().postAuthToken(AuthType.NAVER, accessToken);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(getBaseActivity()).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(getBaseActivity());
                Logger.d("mOAuthLoginHandler " + success + ", errorCode " + errorCode + ", errorDesc " + errorDesc);
            }
        }

        ;
    };
}
