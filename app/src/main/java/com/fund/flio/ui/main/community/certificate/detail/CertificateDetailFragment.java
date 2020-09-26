package com.fund.flio.ui.main.community.certificate.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentCertificateDetailBinding;
import com.fund.flio.databinding.FragmentEventDetailBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;


public class CertificateDetailFragment extends BaseFragment<FragmentCertificateDetailBinding, CertificateDetailViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certificate_detail;
    }

    @Override
    public CertificateDetailViewModel getViewModel() {
        return getViewModelProvider().get(CertificateDetailViewModel.class);
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
