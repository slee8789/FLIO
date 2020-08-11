package com.fund.flio.ui.main.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AuthType;
import com.fund.flio.data.enums.AuthenticationState;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentHomeBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.login.LoginViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    @Inject
    DataManager dataManager;

    @Inject
    BannerAdapter mBannerAdapter;

    @Inject
    RecommendAdapter mRecommendAdapter;

    private LoginViewModel loginViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return getViewModelProvider().get(HomeViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
//        loginViewModel = getViewModelProvider().get(LoginViewModel.class);
//        loginViewModel.getAuthenticationState().observe(this, authenticationObserver);
//        loginViewModel.authenticate(AuthType.valueOf(dataManager.getAuthType()) != AuthType.NONE);
        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        getViewDataBinding().recommends.setAdapter(mRecommendAdapter);
//        getViewDataBinding().recommends.addItemDecoration(new RecyclerDecoration(getBaseActivity()));
        ArrayList<Recommend> testRecommends = new ArrayList<>();
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",true,true,true,"오디오 추천합니다.","30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png",true,true,true,"오디오 추천합니다.","30만원"));
        mRecommendAdapter.addItems(testRecommends);
    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("HomeFragment authenticationObserver " + authenticationState);
        if (authenticationState == AuthenticationState.UNAUTHENTICATED) {
            Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_home_to_nav_intro);

        }
    };

    @Override
    public void goDetail() {
        Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(HomeFragmentDirections.actionNavHomeToNavDetail());
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
