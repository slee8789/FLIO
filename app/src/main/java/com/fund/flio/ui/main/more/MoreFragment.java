package com.fund.flio.ui.main.more;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.databinding.FragmentMoreBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.market.MarketViewModel;
import com.orhanobut.logger.Logger;


public class MoreFragment extends BaseFragment<FragmentMoreBinding, MoreViewModel>  {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public MoreViewModel getViewModel() {
        return getViewModelProvider().get(MoreViewModel.class);
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

    }


}
