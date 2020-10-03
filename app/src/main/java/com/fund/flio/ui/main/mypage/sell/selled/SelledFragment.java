package com.fund.flio.ui.main.mypage.sell.selled;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentSelledBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.mypage.sell.SellListViewModel;
import com.orhanobut.logger.Logger;


public class SelledFragment extends BaseFragment<FragmentSelledBinding, SellListViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selled;
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
