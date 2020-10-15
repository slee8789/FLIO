package com.fund.flio.ui.main.market.register;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentProductRegisterBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.hlab.fabrevealmenu.helper.OnFABMenuSelectedListener;
import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ProductRegisterFragment extends BaseFragment<FragmentProductRegisterBinding, ProductRegisterViewModel> implements OnFABMenuSelectedListener {

    @Inject
    ProductThumbnailAdapter mProductThumbnailAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_register;
    }

    @Override
    public ProductRegisterViewModel getViewModel() {
        return ((MainActivity) getBaseActivity()).getProductRegisterViewModel();
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

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigateUp();
                getViewModel().setInitial();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViews() {
        getViewDataBinding().thumbnails.setAdapter(mProductThumbnailAdapter);
        mProductThumbnailAdapter.setProductRegisterViewModel(getViewModel());
        getViewModel().getThumbnailUris().observe(getViewLifecycleOwner(), thumbnailObserver);
        getViewModel().getProductPrice().observe(getViewLifecycleOwner(), productPriceObserver);
    }

    private final Observer<List<Uri>> thumbnailObserver = thumbnails -> mProductThumbnailAdapter.setItems(thumbnails);

    private String result = "";
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");

    private final Observer<String> productPriceObserver = productPrice -> {
        Logger.d("productPriceObserver " + productPrice);
        if (!TextUtils.isEmpty(productPrice) && !productPrice.equals(result)) {
            result = decimalFormat.format(Double.parseDouble(productPrice.replaceAll(",", "")));
        }
        getViewDataBinding().price.setSelection(getViewDataBinding().price.getText().length());
    };

    @Override
    public void onMenuItemSelected(View view, int id) {

    }
}
