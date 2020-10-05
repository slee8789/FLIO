package com.fund.flio.ui.main.community.event.detail.reply;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentCertificateDetailBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;


public class EventReplyFragment extends BaseFragment<FragmentCertificateDetailBinding, EventReplyViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certificate_detail;
    }

    @Override
    public EventReplyViewModel getViewModel() {
        return getViewModelProvider().get(EventReplyViewModel.class);
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
