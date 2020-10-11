package com.fund.flio.ui.main.market.register.category;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.databinding.FragmentProductRegisterCategoryBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.tabs.TabLayout;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class ProductRegisterCategoryFragment extends BaseFragment<FragmentProductRegisterCategoryBinding, ProductRegisterViewModel> {

    private TabLayout mTabLayout;

    @Inject
    CategoryAdapter mCategoryAdapter;

    private TabLayout.OnTabSelectedListener mainCategory = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    List<String> speaker = Arrays.asList(getResources().getStringArray(R.array.array_category_register_speaker));
                    mCategoryAdapter.setItems(speaker);
                    break;
                case 1:
                    List<String> mike = Arrays.asList(getResources().getStringArray(R.array.array_category_register_mike));
                    mCategoryAdapter.setItems(mike);
                    break;
                case 2:
                    List<String> cable = Arrays.asList(getResources().getStringArray(R.array.array_category_register_cable));
                    mCategoryAdapter.setItems(cable);
                    break;
                case 3:
                    List<String> amp = Arrays.asList(getResources().getStringArray(R.array.array_category_register_amp));
                    mCategoryAdapter.setItems(amp);
                    break;
                case 4:
                    List<String> source = Arrays.asList(getResources().getStringArray(R.array.array_category_register_source));
                    mCategoryAdapter.setItems(source);
                    break;
                case 5:
                    List<String> headset = Arrays.asList(getResources().getStringArray(R.array.array_category_register_headset));
                    mCategoryAdapter.setItems(headset);
                    break;
                case 6:
                    List<String> acoustic = Arrays.asList(getResources().getStringArray(R.array.array_category_register_acoustic));
                    mCategoryAdapter.setItems(acoustic);
                    break;
                case 7:
                    List<String> record = Arrays.asList(getResources().getStringArray(R.array.array_category_register_record));
                    mCategoryAdapter.setItems(record);
                    break;
                case 8:
                    List<String> accessory = Arrays.asList(getResources().getStringArray(R.array.array_category_register_accessory));
                    mCategoryAdapter.setItems(accessory);
                    break;
                case 9:
                    List<String> diy = Arrays.asList(getResources().getStringArray(R.array.array_category_register_diy));
                    mCategoryAdapter.setItems(diy);
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

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_register_category;
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
        mTabLayout = getViewDataBinding().tabs;
        for (int i = 1; i < getResources().getStringArray(R.array.array_category).length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getStringArray(R.array.array_category)[i]).setIcon(getResources().obtainTypedArray(R.array.array_category_icon).getResourceId(i, -1)));
        }
        mTabLayout.addOnTabSelectedListener(mainCategory);

        getViewDataBinding().subs.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setBuyerListViewModel(getViewModel());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical_gray)));
        getViewDataBinding().subs.addItemDecoration(dividerItemDecoration);

        List<String> speaker = Arrays.asList(getResources().getStringArray(R.array.array_category_register_speaker));
        mCategoryAdapter.setItems(speaker);

        getViewModel().getCategoryDepth2().observe(getViewLifecycleOwner(), category -> {
            setCategory(mTabLayout.getSelectedTabPosition(), category);
            getViewModel().setCategory(mTabLayout.getSelectedTabPosition());
        });
    }

    public void setCategory(int position, String category) {
        getViewDataBinding().selected.removeAllViews();
        Chip chip = new Chip(getContext());
        chip.setGravity(Gravity.CENTER);
        chip.setText(format(position, category));
        chip.setEllipsize(TextUtils.TruncateAt.END);
        chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip_entry));
        chip.setTextAppearanceResource(R.style.ChipTextStyle);
        chip.setCheckable(false);
        chip.setOnCloseIconClickListener(v -> {
            getViewModel().getCategoryDepth2().setValue("");
            getViewDataBinding().selected.removeAllViews();
        });
        getViewDataBinding().selected.addView(chip);
    }

    public String format(int position, String category) {

        switch (position) {
            case 0:
                return "스피커 > " + category;
            case 1:
                return "마이크 > " + category;
            case 2:
                return "케이블 > " + category;
            case 3:
                return "앰프 > " + category;
            case 4:
                return "소스기기 > " + category;
            case 5:
                return "이어폰/헤드폰 > " + category;
            case 6:
                return "음향장비 > " + category;
            case 7:
                return "음반 > " + category;
            case 8:
                return "악세사리 > " + category;
            case 9:
                return "DIY > " + category;
        }
        return null;
    }

}
