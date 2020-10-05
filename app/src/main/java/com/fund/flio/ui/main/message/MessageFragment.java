package com.fund.flio.ui.main.message;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentMessageBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;


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
        setupActionBar();

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

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar.toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBaseActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_bookmark, menu);
        super.onCreateOptionsMenu(menu, inflater);
        Logger.d("onCreateOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d("onOptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                Logger.d("onOptionsItemSelected home");
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;

            case R.id.menu_search:
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_global_to_nav_search);
                break;

            case R.id.menu_bookmark:
//                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_global_to_nav_search);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
