package com.fund.flio.ui.main.intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.databinding.FragmentIntroBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.login.LoginNavigator;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.auth.StringSet;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


public class IntroFragment extends BaseFragment<FragmentIntroBinding, LoginViewModel> {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_intro;
    }

    @Override
    public LoginViewModel getViewModel() {
        return getViewModelProvider().get(LoginViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
//        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
        getViewModel().getAuthenticationState().observe(this, authenticationObserver);
        kakaoAutoLogin();
    }

    private void kakaoAutoLogin() {
        Logger.d("kakaoAutoLogin");
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put(StringSet.auto_login, "true");
        Session.getCurrentSession().open(AuthType.KAKAO_TALK_ONLY, this, extraParams); // KAKAO_TALK_ONLY로 실행해야 톡 미설치 시 웹뷰 로그인을 시도하지 않음.
    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("IntroFragment authenticationObserver " + authenticationState);
        if (authenticationState == AuthenticationState.UNAUTHENTICATED) {
            Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_graph_auth);
        } else if(authenticationState == AuthenticationState.AUTHENTICATED) {
            Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_home);
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
