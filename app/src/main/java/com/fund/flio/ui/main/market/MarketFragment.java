package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;


public class MarketFragment extends BaseFragment<FragmentMarketBinding, MarketViewModel> {

    @Inject
    ProductAdapter mProductAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public MarketViewModel getViewModel() {
        return getViewModelProvider().get(MarketViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();
    }

    private void initViews() {

        getViewDataBinding().products.setAdapter(mProductAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().products.addItemDecoration(dividerItemDecoration);
        ArrayList<Product> testProducts = new ArrayList<>();
        testProducts.add(new Product(1,"https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(2,"https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(3,"https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(4,"https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(5,"https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(6,"https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(7,"https://homepages.cae.wisc.edu/~ece533/images/airplane.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(8,"https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", true, true, true, "오디오 추천합니다.", "30만원"));
        testProducts.add(new Product(9,"https://homepages.cae.wisc.edu/~ece533/images/baboon.png", true, true, true, "오디오 추천합니다.", "30만원"));
        mProductAdapter.addItems(testProducts);

        getViewDataBinding().products.setAdapter(mProductAdapter);
        getViewDataBinding().products.addItemDecoration(dividerItemDecoration);
        mProductAdapter.addItems(testProducts);

    }


}
