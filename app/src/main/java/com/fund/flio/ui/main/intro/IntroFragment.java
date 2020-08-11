package com.fund.flio.ui.main.intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentIntroBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;


public class IntroFragment extends BaseFragment<FragmentIntroBinding, IntroViewModel> implements IntroNavigator {

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
    public IntroViewModel getViewModel() {
        return getViewModelProvider().get(IntroViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
//        kakaoAutoLogin();
    }

//    private void kakaoAutoLogin() {
//        Map<String, String> extraParams = new HashMap<>();
//        extraParams.put(StringSet.auto_login, "true");
//        Session.getCurrentSession().open(AuthType.KAKAO_TALK_ONLY, this, extraParams); // KAKAO_TALK_ONLY로 실행해야 톡 미설치 시 웹뷰 로그인을 시도하지 않음.
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showLogin() {
        Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_graph_auth);
    }

    @Override
    public void showHome() {
        Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_intro_to_nav_home);
    }

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
