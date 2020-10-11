package com.fund.flio.ui.main.market;

import android.os.Bundle;
import android.os.Handler;
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
import com.fund.flio.data.enums.AccessoryType;
import com.fund.flio.data.enums.AcousticType;
import com.fund.flio.data.enums.AmpType;
import com.fund.flio.data.enums.CableType;
import com.fund.flio.data.enums.DiyType;
import com.fund.flio.data.enums.HeadSetType;
import com.fund.flio.data.enums.RecordType;
import com.fund.flio.data.enums.MikeType;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.enums.SourceType;
import com.fund.flio.data.enums.SpeakerType;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.utils.GridItemOffsetDecoration;
import com.google.android.material.tabs.TabLayout;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class MarketFragment extends BaseFragment<FragmentMarketBinding, MarketViewModel> implements OnFABMenuSelectedListener {

    private TabLayout mTabLayout;
    private TabLayout mSubTabLayout;

    @Inject
    ProductAdapter mProductAdapter;

    private ProductCategory productCategory;

    private TabLayout.OnTabSelectedListener mainCategory = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Logger.d("onTabSelected " + tab.getPosition());

            switch (tab.getPosition()) {
                case 0:
                    productCategory = ProductCategory.ENTIRE;
                    mSubTabLayout.setVisibility(View.GONE);
                    getViewModel().mainProduct();
                    break;
                case 1:
                    productCategory = ProductCategory.SPEAKER;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_speaker)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 2:
                    productCategory = ProductCategory.MIKE;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_mike)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 3:
                    productCategory = ProductCategory.CABLE;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_cable)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 4:
                    productCategory = ProductCategory.AMP;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_amp)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 5:
                    productCategory = ProductCategory.SOURCE;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_source)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 6:
                    productCategory = ProductCategory.HEADSET;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_headset)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 7:
                    productCategory = ProductCategory.ACOUSTIC;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_acoustic)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 8:
                    productCategory = ProductCategory.RECORD;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_record)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 9:
                    productCategory = ProductCategory.ACCESSORY;
                    mSubTabLayout.setVisibility(View.VISIBLE);
                    mSubTabLayout.removeAllTabs();
                    for (String category : getResources().getStringArray(R.array.array_category_accessory)) {
                        mSubTabLayout.addTab(mSubTabLayout.newTab().setText(category));
                    }
                    break;
                case 10:
                    productCategory = ProductCategory.DIY;
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
    };

    private TabLayout.OnTabSelectedListener subCategory = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            String secondCategory = null;
            switch (productCategory) {
                case SPEAKER:
                    secondCategory = SpeakerType.values()[tab.getPosition()].name();
                    break;
                case MIKE:
                    secondCategory = MikeType.values()[tab.getPosition()].name();
                    break;
                case CABLE:
                    secondCategory = CableType.values()[tab.getPosition()].name();
                    break;
                case AMP:
                    secondCategory = AmpType.values()[tab.getPosition()].name();
                    break;
                case SOURCE:
                    secondCategory = SourceType.values()[tab.getPosition()].name();
                    break;
                case HEADSET:
                    secondCategory = HeadSetType.values()[tab.getPosition()].name();
                    break;
                case ACOUSTIC:
                    secondCategory = AcousticType.values()[tab.getPosition()].name();
                    break;
                case RECORD:
                    secondCategory = RecordType.values()[tab.getPosition()].name();
                    break;
                case ACCESSORY:
                    secondCategory = AccessoryType.values()[tab.getPosition()].name();
                    break;
                case DIY:
                    secondCategory = DiyType.values()[tab.getPosition()].name();
                    break;

            }
            if (secondCategory.equals(ProductCategory.ENTIRE.name())) {
                getViewModel().recommandProducts(productCategory.name(), null);
            } else {
                getViewModel().recommandProducts(productCategory.name(), secondCategory);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

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
        getViewModel().mainProduct();
        Logger.i("onViewCreated");
    }

    private void initViews() {
        mTabLayout = getViewDataBinding().tabs;
        for (String category : getResources().getStringArray(R.array.array_category)) {
            mTabLayout.addTab(mTabLayout.newTab().setText(category));
        }
        mTabLayout.addOnTabSelectedListener(mainCategory);
        mSubTabLayout = getViewDataBinding().subTabs;
        mSubTabLayout.addOnTabSelectedListener(subCategory);
        productCategory = MarketFragmentArgs.fromBundle(getArguments()).getProductCategory();


        new Handler().postDelayed(() -> {
            mTabLayout.getTabAt(productCategory.ordinal()).select();
        }, 100);

        getViewDataBinding().products.setLayoutManager(new GridLayoutManager(getBaseActivity(), 2));
        getViewDataBinding().products.setAdapter(mProductAdapter);
        GridItemOffsetDecoration itemDecoration = new GridItemOffsetDecoration(getBaseActivity(), R.dimen.grid_item_offset);
        getViewDataBinding().products.addItemDecoration(itemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductAdapter.setItems(products));
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
