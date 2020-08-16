package com.fund.flio.ui.main.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.fund.flio.utils.RecyclerDecoration;
import com.fund.flio.utils.ViewUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    @Inject
    DataManager dataManager;

    @Inject
    BannerAdapter mBannerAdapter;

    @Inject
    RecommendAdapter mRecommendAdapter;

    @Inject
    CertificatedAdapter mCertificatedAdapter;

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
        loginViewModel = getViewModelProvider().get(LoginViewModel.class);
        loginViewModel.getAuthenticationState().observe(this, authenticationObserver);
//        loginViewModel.authenticate(AuthType.valueOf(dataManager.getAuthType()) != AuthType.NONE);
//        getViewModel().setNadvigator(this);
//        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_app_bar_default, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//        Logger.d("onCreateOptionsMenu");
//    }
//
//    @Override
//    public void onPrepareOptionsMenu(@NonNull Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        Logger.d("onPrepareOptionsMenu");
////        menuCompleted = menu.findItem(R.id.menu_done);
////        menuCompleted.setEnabled(false);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
////            case R.id.menu_done:
////                getViewModel().onComplete();
////                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {

        getViewDataBinding().recommends.setAdapter(mRecommendAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider)));
        getViewDataBinding().recommends.addItemDecoration(dividerItemDecoration);
        ArrayList<Recommend> testRecommends = new ArrayList<>();
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testRecommends.add(new Recommend("https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        mRecommendAdapter.addItems(testRecommends);

        getViewDataBinding().certificates.setAdapter(mCertificatedAdapter);
        getViewDataBinding().certificates.addItemDecoration(dividerItemDecoration);
        mCertificatedAdapter.addItems(testRecommends);

    }

    private final Observer<AuthenticationState> authenticationObserver = authenticationState -> {
        Logger.d("HomeFragment authenticationObserver " + authenticationState);
        if (authenticationState == AuthenticationState.UNAUTHENTICATED) {
            Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_home_to_nav_intro);

        }
    };

}
