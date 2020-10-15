package com.fund.flio.ui.main.category;

import android.os.Bundle;
import android.os.Handler;
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
import com.fund.flio.data.enums.MikeType;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.enums.RecordType;
import com.fund.flio.data.enums.SourceType;
import com.fund.flio.data.enums.SpeakerType;
import com.fund.flio.databinding.FragmentCategoryBinding;
import com.fund.flio.databinding.FragmentMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.MarketFragmentArgs;
import com.fund.flio.ui.main.market.ProductAdapter;
import com.fund.flio.utils.GridItemOffsetDecoration;
import com.google.android.material.tabs.TabLayout;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel> implements OnFABMenuSelectedListener {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public CategoryViewModel getViewModel() {
        return getViewModelProvider().get(CategoryViewModel.class);
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
        initViews();
        setupActionBar();
//        getViewModel().mainProduct();
        Logger.i("onViewCreated");
    }

    private void initViews() {

    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar.toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getViewDataBinding().toolbar.search.setOnClickListener(v -> Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_search));
        getViewDataBinding().toolbar.favorite.setOnClickListener(v -> Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_global_to_nav_favorite_list));
    }

    @Override
    public void onMenuItemSelected(View view, int id) {

    }

}
