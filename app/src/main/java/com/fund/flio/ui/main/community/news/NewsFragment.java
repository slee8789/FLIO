package com.fund.flio.ui.main.community.news;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.News;
import com.fund.flio.databinding.FragmentNewsBinding;
import com.fund.flio.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel> {

    private TabLayout mTabLayout;

    @Inject
    NewsAdapter mNewsAdapter;

    private TabLayout.OnTabSelectedListener newsCategory = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            getViewModel().searchKeyword(getResources().getStringArray(R.array.array_community_news)[tab.getPosition()]);
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
        return R.layout.fragment_news;
    }

    @Override
    public NewsViewModel getViewModel() {
        return getViewModelProvider().get(NewsViewModel.class);
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
        Logger.d("onViewCreated");
        initViews();
    }

    private void initViews() {
        mTabLayout = getViewDataBinding().tabs;
        mTabLayout.addOnTabSelectedListener(newsCategory);
        for (String category : getResources().getStringArray(R.array.array_community_news)) {
            mTabLayout.addTab(mTabLayout.newTab().setText(category));
        }

        getViewModel().getSearchs().observe(getViewLifecycleOwner(), products -> mNewsAdapter.setItems(products));

        getViewDataBinding().news.setAdapter(mNewsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().news.addItemDecoration(dividerItemDecoration);
//        ArrayList<News> dummyNewses = new Gson().fromJson(readAssetJson(getContext(), "news.json"), new TypeToken<List<News>>() {
//        }.getType());
//        mNewsAdapter.addItems(dummyNewses);
        getViewModel().searchKeyword(getResources().getStringArray(R.array.array_community_news)[0]);
    }


}
