package com.fund.flio.ui.main.search;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentAlarmBinding;
import com.fund.flio.databinding.FragmentSearchBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        return getViewModelProvider().get(SearchViewModel.class);
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
        ArrayList<String> dummyPopularTag = new Gson().fromJson(readAssetJson(getContext(), "tag_popular.json"), new TypeToken<List<String>>() {
        }.getType());
        for (String tag : dummyPopularTag) {
            Chip chip = new Chip(getContext());
            chip.setGravity(Gravity.CENTER);
            chip.setText(tag);
            chip.setTextAppearanceResource(R.style.ChipTextStyle);
            chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip));

            getViewDataBinding().tagPopular.addView(chip);
        }
    }

}
