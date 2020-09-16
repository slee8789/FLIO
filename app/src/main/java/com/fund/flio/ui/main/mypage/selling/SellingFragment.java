package com.fund.flio.ui.main.mypage.selling;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentSellListBinding;
import com.fund.flio.databinding.FragmentSellingBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.message.MessagePagerAdapter;
import com.fund.flio.ui.main.mypage.selllist.SellListViewModel;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;


public class SellingFragment extends BaseFragment<FragmentSellingBinding, SellListViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selling;
    }

    @Override
    public SellListViewModel getViewModel() {
        return getViewModelProvider().get(SellListViewModel.class);
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

    }

}
