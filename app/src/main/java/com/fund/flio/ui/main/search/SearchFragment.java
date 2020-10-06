package com.fund.flio.ui.main.search;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.data.model.SearchResult;
import com.fund.flio.databinding.FragmentSearchBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.home.ProductSmallAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import gun0912.tedkeyboardobserver.TedRxKeyboardObserver;
import io.reactivex.disposables.Disposable;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {

    @Inject
    ProductSmallAdapter mProductSmallAdapter;

    @Inject
    SearchRecentAdapter mSearchRecentAdapter;

    private Disposable keyboardDisposable;

    private InputMethodManager imm;

    private Fade mFadeIn = new Fade(Fade.IN);
    private Fade mFadeOut = new Fade(Fade.OUT);

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

        keyboardDisposable = new TedRxKeyboardObserver(getBaseActivity())
                .listen()
                .subscribe(isShow -> {
                    getViewModel().setIsKeyboardShow(isShow);
                    TransitionManager.beginDelayedTransition(getViewDataBinding().root, isShow ? mFadeIn : mFadeOut);
                }, onError -> Logger.e("keyboard observer error " + onError));

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().setMainViewModel(getMainViewModel());
        initViews();
        setupActionBar();
        imm = (InputMethodManager) getBaseActivity().getSystemService(INPUT_METHOD_SERVICE);
    }

    private void initViews() {
        ArrayList<String> dummyPopularTag = new Gson().fromJson(readAssetJson(getContext(), "tag_popular.json"), new TypeToken<List<String>>() {
        }.getType());
        for (String tag : dummyPopularTag) {
            Chip chip = new Chip(getContext());
            chip.setGravity(Gravity.CENTER);
            chip.setText(tag);
            chip.setChipDrawable(ChipDrawable.createFromResource(getContext(), R.xml.chip_action));
            chip.setTextAppearanceResource(R.style.ChipTextStyle);
//            chipAction.setOnClickListener(v -> {
//                getViewModel().setIsLoading(!getViewModel().getIsLoading().get());
//            });
            getViewDataBinding().tagPopular.addView(chip);
        }

        getViewDataBinding().recommends.setAdapter(mProductSmallAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_horizontal)));
        getViewDataBinding().recommends.addItemDecoration(dividerItemDecoration);
        ArrayList<Product> testRecommends = new Gson().fromJson(readAssetJson(getContext(), "products.json"), new TypeToken<List<Product>>() {
        }.getType());
        mProductSmallAdapter.addItems(testRecommends);

        getViewDataBinding().recents.setAdapter(mSearchRecentAdapter);
        mSearchRecentAdapter.setSearchViewModel(getViewModel());

        getViewDataBinding().search.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    Logger.d("ACTION SEARCH " + getViewDataBinding().search.getText().toString());
                    if (getViewDataBinding().search.getText().length() != 0) {
                        getViewModel().onDataInsert(getViewDataBinding().search.getText().toString());
                        getViewDataBinding().search.clearFocus();
                        imm.hideSoftInputFromWindow(getViewDataBinding().search.getWindowToken(), 0);
                    }
                    break;
            }
            return false;
        });

        getViewModel().getSearchResults().observe(getViewLifecycleOwner(), searchResultObserver);

    }

    private void setupActionBar() {
        getBaseActivity().setSupportActionBar(getViewDataBinding().toolbar);
        getBaseActivity().getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBaseActivity().getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
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

    private final Observer<List<SearchResult>> searchResultObserver = searchResults -> {
        Logger.d("searchResultObserver " + searchResults);
        mSearchRecentAdapter.setItems(searchResults);
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        keyboardDisposable.dispose();
    }
}
