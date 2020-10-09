package com.fund.flio.ui.main.market.register.tag;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentProductRegisterTagBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.orhanobut.logger.Logger;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ProductRegisterTagFragment extends BaseFragment<FragmentProductRegisterTagBinding, ProductRegisterViewModel> {


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_register_tag;
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        getViewModel().getInputTags().observe(getViewLifecycleOwner(), inputTagObserver);
    }

    private int inputTagLength;

    private final Observer<String> inputTagObserver = inputTags -> {
        Logger.d("inputTagObserver " + inputTags);
        Logger.d("inputTagObserver inputTagLength " + inputTagLength);
        if (inputTagLength < inputTags.length()) {
            if (inputTags.endsWith(" ")) {
                if (inputTags.endsWith(" #")) {
                    Logger.d("test 1");

                } else {
                    Logger.d("test 2");
                    getViewDataBinding().input.setText(inputTags.trim() + " #");
                    inputTagLength = getViewDataBinding().input.length();
                    getViewDataBinding().input.setSelection(getViewDataBinding().input.getText().length());
                }
            }
        }

    };
}
