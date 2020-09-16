package com.fund.flio.ui.main.community.certificate;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Certificate;
import com.fund.flio.databinding.FragmentCertificateBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class CertificateFragment extends BaseFragment<FragmentCertificateBinding, CertificateViewModel> {

    private TabLayout mTabLayout;

    @Inject
    CertificateAdapter mCertificateAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certificate;
    }

    @Override
    public CertificateViewModel getViewModel() {
        return getViewModelProvider().get(CertificateViewModel.class);
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
        mTabLayout = getViewDataBinding().tabs;
        mTabLayout.addTab(mTabLayout.newTab().setText("전체"));
        mTabLayout.addTab(mTabLayout.newTab().setText("My플리오"));
        mTabLayout.addTab(mTabLayout.newTab().setText("오디오리뷰"));
        mTabLayout.addTab(mTabLayout.newTab().setText("거래인증"));

        getViewDataBinding().certificates.setAdapter(mCertificateAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().certificates.addItemDecoration(dividerItemDecoration);
        ArrayList<Certificate> dummyCertificates = new Gson().fromJson(readAssetJson(getContext(), "certificates.json"), new TypeToken<List<Certificate>>() {
        }.getType());
        mCertificateAdapter.addItems(dummyCertificates);
    }


}
