package com.fund.flio.ui.main.community.certificate.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.annimon.stream.Stream;
import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.CertificateType;
import com.fund.flio.data.enums.EventType;
import com.fund.flio.data.model.Certificate;
import com.fund.flio.databinding.FragmentCertificateListBinding;
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


public class CertificateListFragment extends BaseFragment<FragmentCertificateListBinding, CertificateListViewModel> {

    private TabLayout mTabLayout;

    @Inject
    CertificateAdapter mCertificateAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certificate_list;
    }

    @Override
    public CertificateListViewModel getViewModel() {
        return getViewModelProvider().get(CertificateListViewModel.class);
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

        for (String category : getResources().getStringArray(R.array.array_community_certificate)) {
            mTabLayout.addTab(mTabLayout.newTab().setText(category));
        }

        ArrayList<Certificate> dummyCertificates = new Gson().fromJson(readAssetJson(getContext(), "certificates.json"), new TypeToken<List<Certificate>>() {
        }.getType());

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Logger.d("TabSelected " + tab.getPosition());
                if (tab.getPosition() == 0) {
                    mCertificateAdapter.setItems(dummyCertificates);
                } else {
                    mCertificateAdapter.setItems(Stream.of(dummyCertificates).filter(certificate -> tab.getPosition() == CertificateType.valueOf(certificate.getCategory()).ordinal()).toList());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getViewDataBinding().certificates.setAdapter(mCertificateAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().certificates.addItemDecoration(dividerItemDecoration);

        mCertificateAdapter.addItems(dummyCertificates);
    }


}
