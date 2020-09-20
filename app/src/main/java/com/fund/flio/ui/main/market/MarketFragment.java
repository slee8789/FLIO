package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.utils.GridItemOffsetDecoration;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;

public class MarketFragment extends BaseFragment<FragmentMarketBinding, MarketViewModel> {

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
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().setMainViewModel(getMainViewModel());
        initViews();
    }

    private void initViews() {
        mTabLayout = getViewDataBinding().tabs;
        mTabLayout.addTab(mTabLayout.newTab().setText("전체"));
        mTabLayout.addTab(mTabLayout.newTab().setText("스피커"));
        mTabLayout.addTab(mTabLayout.newTab().setText("마이크"));
        mTabLayout.addTab(mTabLayout.newTab().setText("케이블"));
        mTabLayout.addTab(mTabLayout.newTab().setText("앰프"));
        mTabLayout.addTab(mTabLayout.newTab().setText("소스기기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("이어폰/헤드셋"));
        mTabLayout.addTab(mTabLayout.newTab().setText("음향장비"));
        mTabLayout.addTab(mTabLayout.newTab().setText("음반/악기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("악세사리"));
        mTabLayout.addTab(mTabLayout.newTab().setText("DIY"));
        mTabLayout.addTab(mTabLayout.newTab().setText("헤드폰"));
        mTabLayout.addTab(mTabLayout.newTab().setText("악기"));

        mSubTabLayout = getViewDataBinding().subTabs;
        mSubTabLayout.addTab(mSubTabLayout.newTab().setText("1인방송"));
        mSubTabLayout.addTab(mSubTabLayout.newTab().setText("홈레코딩"));
        mSubTabLayout.addTab(mSubTabLayout.newTab().setText("하이파이"));
        mSubTabLayout.addTab(mSubTabLayout.newTab().setText("프로장비"));


        getViewDataBinding().products.setLayoutManager(new GridLayoutManager(getBaseActivity(), 2));
        getViewDataBinding().products.setAdapter(mProductAdapter);
        GridItemOffsetDecoration itemDecoration = new GridItemOffsetDecoration(getBaseActivity(), R.dimen.grid_item_offset);
        getViewDataBinding().products.addItemDecoration(itemDecoration);
        ArrayList<Product> testProducts = new Gson().fromJson(readAssetJson(getBaseActivity(), "recommands.json"), new TypeToken<List<Product>>() {
        }.getType());
        mProductAdapter.addItems(testProducts);

        getViewDataBinding().products.setAdapter(mProductAdapter);
        mProductAdapter.addItems(testProducts);

    }


}
