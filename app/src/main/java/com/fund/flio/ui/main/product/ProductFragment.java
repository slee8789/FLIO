package com.fund.flio.ui.main.product;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentFavoriteBinding;
import com.fund.flio.databinding.FragmentProductBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.home.HomeViewModel;
import com.fund.flio.ui.main.home.RecommendAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class ProductFragment extends BaseFragment<FragmentProductBinding, ProductViewModel>  {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    RecommendAdapter mRecommendAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        return getViewModelProvider().get(ProductViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
//        getViewModel().setNavigator(this);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
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

    }


}
