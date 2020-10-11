package com.fund.flio.ui.main.mypage.sell.selled;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.FragmentSelledBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.mypage.sell.SellListViewModel;
import com.fund.flio.ui.main.mypage.sell.selling.ProductSellingAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class SelledFragment extends BaseFragment<FragmentSelledBinding, SellListViewModel> {

    @Inject
    ProductSelledAdapter mProductSelledAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selled;
    }

    @Override
    public SellListViewModel getViewModel() {
        return getViewModelProvider().get(SellListViewModel.class);
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
        getViewDataBinding().selled.setAdapter(mProductSelledAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_8)));
        getViewDataBinding().selled.addItemDecoration(dividerItemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductSelledAdapter.setItems(products));
        getViewModel().myPageProduct(SaleYn.Y);
    }

}
