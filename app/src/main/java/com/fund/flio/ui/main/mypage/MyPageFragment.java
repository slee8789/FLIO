package com.fund.flio.ui.main.mypage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Keyword;
import com.fund.flio.data.model.SearchResult;
import com.fund.flio.databinding.FragmentMyPageBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static com.fund.flio.utils.ViewUtils.getStatusBarHeight;
import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class MyPageFragment extends BaseFragment<FragmentMyPageBinding, MyPageViewModel> {



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_page;
    }

    @Override
    public MyPageViewModel getViewModel() {
        return getViewModelProvider().get(MyPageViewModel.class);
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
        setupActionBar();
        initViews();
    }

    private void initViews() {
        getViewDataBinding().keyword.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    getViewModel().onKeywordRegisterClick(v);

                    return true;
            }
            return false;
        });
        getViewModel().getKeyword().observe(getViewLifecycleOwner(), keywordObserver);
        getViewModel().getKeywords().observe(getViewLifecycleOwner(), keywordsObserver);
    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_setting, menu);
        super.onCreateOptionsMenu(menu, inflater);
        Logger.d("onCreateOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d("onOptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Logger.d("onOptionsItemSelected menu_setting");
                Navigation.findNavController(getBaseActivity(), R.id.fragment_container).navigate(R.id.action_nav_my_page_to_nav_setting);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final Observer<String> keywordObserver = keyword -> {
//        if (keyword.length() == 0)
//            imm.hideSoftInputFromWindow(getViewDataBinding().keyword.getWindowToken(), 0);
    };

    private final Observer<List<Keyword>> keywordsObserver = keywords -> {
        getViewDataBinding().tagKeyword.removeAllViews();
        for (Keyword keyword : keywords) {
            Chip chip = new Chip(getContext());
            chip.setGravity(Gravity.CENTER);
            chip.setText(keyword.getKeyword());
            chip.setEllipsize(TextUtils.TruncateAt.END);
            chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip_entry));
            chip.setTextAppearanceResource(R.style.ChipTextStyle);
            chip.setCheckable(false);
            chip.setOnCloseIconClickListener(v -> getViewModel().onKeywordDelete(keyword));
            getViewDataBinding().tagKeyword.addView(chip);
        }

    };

}
