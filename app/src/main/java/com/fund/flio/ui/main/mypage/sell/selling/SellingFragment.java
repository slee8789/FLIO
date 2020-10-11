package com.fund.flio.ui.main.mypage.sell.selling;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.FragmentSellingBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.mypage.sell.SellListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class SellingFragment extends BaseFragment<FragmentSellingBinding, SellListViewModel> {

    @Inject
    ProductSellingAdapter mProductSellingAdapter;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selling;
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
        getViewDataBinding().sellings.setAdapter(mProductSellingAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_8)));
        getViewDataBinding().sellings.addItemDecoration(dividerItemDecoration);
        getViewModel().getProducts().observe(getViewLifecycleOwner(), products -> mProductSellingAdapter.setItems(products));
        getViewModel().myPageProduct(SaleYn.S);

    }


    ;

}
