package com.fund.flio.ui.main.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.databinding.FragmentLoginBinding;
import com.fund.flio.databinding.FragmentProfileBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.AuthViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static com.fund.flio.core.AppConstant.RC_GOOGLE_SIGN_IN;


public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public ProfileViewModel getViewModel() {
        return getViewModelProvider().get(ProfileViewModel.class);
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
        initViews();
    }

    private void initViews() {

    }

}
