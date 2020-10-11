package com.fund.flio.ui.main.mypage.buy;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.databinding.FragmentBuyListBinding;
import com.fund.flio.databinding.FragmentSellListBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.mypage.sell.SellListPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import java.util.Objects;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;


public class BuyListFragment extends BaseFragment<FragmentBuyListBinding, BuyListViewModel> {

    @Inject
    ProductBuyAdapter mProductBuyAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buy_list;
    }

    @Override
    public BuyListViewModel getViewModel() {
        return getViewModelProvider().get(BuyListViewModel.class);
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
        setupActionBar();
    }

    private void initViews() {
        Logger.d("initViews");
        getViewDataBinding().buys.setAdapter(mProductBuyAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_8)));
        getViewDataBinding().buys.addItemDecoration(dividerItemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductBuyAdapter.setItems(products));
        getViewModel().targetProduct();
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume");

    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d("onOptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                Logger.d("onOptionsItemSelected home");
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
