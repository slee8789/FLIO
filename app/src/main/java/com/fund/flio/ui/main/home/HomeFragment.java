package com.fund.flio.ui.main.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Banner;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentHomeBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    BannerAdapter mBannerAdapter;

    @Inject
    RecommendAdapter mRecommendAdapter;

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
        return new ViewModelProvider(getViewModelStore(), viewModelProviderFactory).get(HomeViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
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