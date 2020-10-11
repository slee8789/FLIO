package com.fund.flio.ui.main.mypage.favorite.market;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentFavoriteListBinding;
import com.fund.flio.databinding.FragmentFavoriteMarketBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.Objects;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;


public class FavoriteProductFragment extends BaseFragment<FragmentFavoriteMarketBinding, FavoriteProductViewModel> {

    @Inject
    FavoriteProductAdapter mFavoriteProductAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favorite_market;
    }

    @Override
    public FavoriteProductViewModel getViewModel() {
        return getViewModelProvider().get(FavoriteProductViewModel.class);
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
        setupActionBar();
    }

    private void initViews() {
        getViewDataBinding().buys.setAdapter(mFavoriteProductAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_8)));
        getViewDataBinding().buys.addItemDecoration(dividerItemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mFavoriteProductAdapter.setItems(products));
        getViewModel().targetProduct();
    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d("onOptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                Logger.d("onOptionsItemSelected home");
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
