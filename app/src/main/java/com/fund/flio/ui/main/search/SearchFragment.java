package com.fund.flio.ui.main.search;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.FragmentAlarmBinding;
import com.fund.flio.databinding.FragmentSearchBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.home.RecommendAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {

    @Inject
    RecommendAdapter mRecommendAdapter;

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
//        Logger.i("onCreate");
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
            chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip));
            chip.setTextAppearanceResource(R.style.ChipTextStyle);
            getViewDataBinding().tagPopular.addView(chip);
        }

        getViewDataBinding().recommends.setAdapter(mRecommendAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_horizontal)));
        getViewDataBinding().recommends.addItemDecoration(dividerItemDecoration);
        ArrayList<Recommend> testRecommends = new Gson().fromJson(readAssetJson(getContext(), "recommands.json"), new TypeToken<List<Recommend>>() {
        }.getType());
        mRecommendAdapter.addItems(testRecommends);

        getViewDataBinding().searchView.setOnClickListener(v -> Logger.d("onSearchClick"));
        getViewDataBinding().searchView.setOnFocusChangeListener((v, hasFocus) -> {
            Logger.d("onFocus " + hasFocus);
        });
        getViewDataBinding().searchView.setOnCloseListener(() -> {
            Logger.d("onClose ");
            return false;
        });
    }

}
