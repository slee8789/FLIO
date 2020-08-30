package com.fund.flio.ui.main.home;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentHomeBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.firebase.auth.FirebaseAuth;
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
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();

        getViewDataBinding().headerRecommends.setOnClickListener(v -> {
//            Logger.d("Firebase Auth " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        });
    }

    private void initViews() {

        getViewDataBinding().recommends.setAdapter(mRecommendAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_horizontal)));
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
}
