package com.fund.flio.ui.main.community.certificate.detail.reply;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentCertificateDetailBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;


public class CertificateReplyFragment extends BaseFragment<FragmentCertificateDetailBinding, CertificateReplyViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certificate_detail;
    }

    @Override
    public CertificateReplyViewModel getViewModel() {
        return getViewModelProvider().get(CertificateReplyViewModel.class);
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
