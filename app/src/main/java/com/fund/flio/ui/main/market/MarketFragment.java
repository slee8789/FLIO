package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.transition.TransitionInflater;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.utils.GridItemOffsetDecoration;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static com.fund.flio.utils.ViewUtils.readAssetJson;

public class MarketFragment extends BaseFragment<FragmentMarketBinding, MarketViewModel> implements OnFABMenuSelectedListener {

    private TabLayout mTabLayout;
    private TabLayout mSubTabLayout;

    @Inject
    ProductAdapter mProductAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public MarketViewModel getViewModel() {
        return getViewModelProvider().get(MarketViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate");
        setHasOptionsMenu(true);

        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

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
        for (String category : getResources().getStringArray(R.array.array_category)) {
            mTabLayout.addTab(mTabLayout.newTab().setText(category));
        }
        mSubTabLayout = getViewDataBinding().subTabs;
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mSubTabLayout.setVisibility(View.GONE);
                        break;
                    case 1:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_speaker)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 2:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_mike)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 3:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_cable)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 4:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_amp)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 5:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_source)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 6:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_headset)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 7:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_acoustic)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 8:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_instrument)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 9:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_accessory)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;
                    case 10:
                        mSubTabLayout.setVisibility(View.VISIBLE);
                        mSubTabLayout.removeAllTabs();
                        for (String category : getResources().getStringArray(R.array.array_category_diy)) {
                            mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                        }
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getViewDataBinding().products.setLayoutManager(new GridLayoutManager(getBaseActivity(), 2));
        getViewDataBinding().products.setAdapter(mProductAdapter);
        GridItemOffsetDecoration itemDecoration = new GridItemOffsetDecoration(getBaseActivity(), R.dimen.grid_item_offset);
        getViewDataBinding().products.addItemDecoration(itemDecoration);
        ArrayList<Product> testProducts = new Gson().fromJson(readAssetJson(getBaseActivity(), "products.json"), new TypeToken<List<Product>>() {
        }.getType());
        mProductAdapter.addItems(testProducts);

        getViewDataBinding().products.setAdapter(mProductAdapter);
        mProductAdapter.addItems(testProducts);
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

    @Override
    public void onMenuItemSelected(View view, int id) {

    }
}
