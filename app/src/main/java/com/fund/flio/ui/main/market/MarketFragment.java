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
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readMovieJson;


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
        ArrayList<Product> testProducts = new Gson().fromJson(readMovieJson(getContext(),"recommands.json"), new TypeToken<List<Product>>() {
        }.getType());
        mProductAdapter.addItems(testProducts);

        getViewDataBinding().products.setAdapter(mProductAdapter);
        getViewDataBinding().products.addItemDecoration(dividerItemDecoration);
        mProductAdapter.addItems(testProducts);

    }


}
