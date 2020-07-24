package com.fund.flio.ui.main.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentLoginBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;


public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    OAuthLogin mOAuthLoginModule;

    @Inject
    GoogleSignInClient mGoogleSignInClient;

    private OAuthLoginButton mOAuthLoginButton;

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
        return new ViewModelProvider(getViewModelStore(), viewModelProviderFactory).get(LoginViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mOAuthLoginButton = (OAuthLoginButton) getViewDataBinding().btnNaver;
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
        getViewDataBinding().btnGoogle.setOnClickListener(v -> getBaseActivity().startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_GOOGLE_SIGN_IN));

        //Logout Test
        getViewDataBinding().btnNaverLogout.setOnClickListener(v -> {
            mOAuthLoginModule.logoutAndDeleteToken(getBaseActivity());
        });

        getViewDataBinding().btnGoogleLogout.setOnClickListener(v -> {
            ((MainActivity)getBaseActivity()).getViewModel().googleLogout();
        });

        getViewDataBinding().btnKakaoLogout.setOnClickListener(v -> {
            UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    Logger.d("kakao logout success");
                }
            });

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
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(getBaseActivity()).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(getBaseActivity());
                Logger.d("mOAuthLoginHandler " + success + ", errorCode " + errorCode + ", errorDesc " + errorDesc);
            }
        }

        ;
    };


    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleError(Throwable throwable) {
        Logger.e("handleError " + throwable.getMessage());
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }
}
