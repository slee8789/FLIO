package com.fund.flio.ui.main.mypage.sell.selling.buyer.guide;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.UseDate;
import com.fund.flio.databinding.FragmentBuyerGuideBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.market.product.detail.ProductDetailDialogArgs;
import com.orhanobut.logger.Logger;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class BuyerGuideFragment extends BaseFragment<FragmentBuyerGuideBinding, BuyerGuideViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buyer_guide;
    }

    @Override
    public BuyerGuideViewModel getViewModel() {
        return getViewModelProvider().get(BuyerGuideViewModel.class);
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
        getViewModel().imageUrl.set(BuyerGuideFragmentArgs.fromBundle(getArguments()).getProductImage());
        getViewModel().productName.set(BuyerGuideFragmentArgs.fromBundle(getArguments()).getTitle());
        getViewModel().productId.set(BuyerGuideFragmentArgs.fromBundle(getArguments()).getProductId());
//        binding.useDate.setText(UseDate.valueOf(ProductDetailDialogArgs.fromBundle(getArguments()).getProduct().getUseDate()).getType());
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
