package com.fund.flio.ui.main.message;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentMessageBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;


public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageViewModel> {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public MessageViewModel getViewModel() {
        return getViewModelProvider().get(MessageViewModel.class);
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
        mTabLayout.addTab(mTabLayout.newTab().setText("채팅"));
        mTabLayout.addTab(mTabLayout.newTab().setText("댓글"));
        mViewPager = getViewDataBinding().pager;
        final MessagePagerAdapter adapter = new MessagePagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
