package com.fund.flio.ui.main.community;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentCommunityBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding, CommunityViewModel> {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public CommunityViewModel getViewModel() {
        return getViewModelProvider().get(CommunityViewModel.class);
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
        getViewDataBinding().setMainViewModel(getMainViewModel());
        initViews();

    }

    private void initViews() {
        mTabLayout = getViewDataBinding().tabs;
        mTabLayout.addTab(mTabLayout.newTab().setText("플리오 뉴스"));
        mTabLayout.addTab(mTabLayout.newTab().setText("플리오 인증"));
        mTabLayout.addTab(mTabLayout.newTab().setText("홍보/행사"));
        mViewPager = getViewDataBinding().pager;
        final CommunityPagerAdapter adapter = new CommunityPagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
