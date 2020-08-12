package com.fund.flio.ui.main.channel;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentChannelBinding;
import com.fund.flio.databinding.FragmentDetailBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;


public class ChannelFragment extends BaseFragment<FragmentChannelBinding, ChannelViewModel>  {

    public static final String TAG = ChannelFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_channel;
    }

    @Override
    public ChannelViewModel getViewModel() {
        return new ViewModelProvider(getViewModelStore(), viewModelProviderFactory).get(ChannelViewModel.class);
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

    }
}
