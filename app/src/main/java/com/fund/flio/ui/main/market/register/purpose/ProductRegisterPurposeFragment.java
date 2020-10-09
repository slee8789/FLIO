package com.fund.flio.ui.main.market.register.purpose;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.enums.Purpose;
import com.fund.flio.databinding.FragmentProductRegisterPurposeBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.orhanobut.logger.Logger;

import java.util.List;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ProductRegisterPurposeFragment extends BaseFragment<FragmentProductRegisterPurposeBinding, ProductRegisterViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_register_purpose;
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
        getViewModel().getPurposes().observe(getViewLifecycleOwner(), purposeObserver);
        Logger.d("initViews purpose " + getViewModel().getPurposes().getValue().size());
        for (Purpose purpose : getViewModel().getPurposes().getValue()) {
            switch (Purpose.valueOf(purpose.name())) {
                case A:
                    getViewDataBinding().purposeA.setChecked(true);
                    break;
                case B:
                    getViewDataBinding().purposeB.setChecked(true);
                    break;
                case C:
                    getViewDataBinding().purposeC.setChecked(true);
                    break;
                case D:
                    getViewDataBinding().purposeD.setChecked(true);
                    break;
                case E:
                    getViewDataBinding().purposeE.setChecked(true);
                    break;
                case F:
                    getViewDataBinding().purposeF.setChecked(true);
                    break;
                case G:
                    getViewDataBinding().purposeG.setChecked(true);
                    break;
                case H:
                    getViewDataBinding().purposeH.setChecked(true);
                    break;
            }
        }
    }

    private void initialPurpose(Purpose purpose) {
        switch (Purpose.valueOf(purpose.name())) {
            case A:
                getViewDataBinding().purposeA.performClick();
                break;
            case B:
                getViewDataBinding().purposeB.performClick();
                break;
            case C:
                getViewDataBinding().purposeC.performClick();
                break;
            case D:
                getViewDataBinding().purposeD.performClick();
                break;
            case E:
                getViewDataBinding().purposeE.performClick();
                break;
            case F:
                getViewDataBinding().purposeF.performClick();
                break;
            case G:
                getViewDataBinding().purposeG.performClick();
                break;
            case H:
                getViewDataBinding().purposeH.performClick();
                break;
        }
    }

    private final Observer<List<Purpose>> purposeObserver = purposes -> {
        getViewDataBinding().selectedPurpose.removeAllViews();
        for (Purpose purpose : purposes) {
            Chip chip = new Chip(getContext());
            chip.setGravity(Gravity.CENTER);
            chip.setText(purpose.getType());
            chip.setEllipsize(TextUtils.TruncateAt.END);
            chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip_entry));
            chip.setTextAppearanceResource(R.style.ChipTextStyle);
            chip.setCheckable(false);
            chip.setOnCloseIconClickListener(v -> {
                getViewModel().onPurposeDelete(purpose);
                initialPurpose(purpose);
            });
            getViewDataBinding().selectedPurpose.addView(chip);
        }

    };

}
