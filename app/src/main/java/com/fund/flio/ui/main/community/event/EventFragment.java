package com.fund.flio.ui.main.community.event;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fund.flio.BR;
import com.fund.flio.R;
import com.fund.flio.data.model.Event;
import com.fund.flio.data.model.News;
import com.fund.flio.databinding.FragmentEventBinding;
import com.fund.flio.databinding.FragmentNewsBinding;
import com.fund.flio.di.ViewModelProviderFactory;
import com.fund.flio.ui.base.BaseFragment;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.ui.main.community.news.NewsAdapter;
import com.fund.flio.ui.main.community.news.NewsViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import static com.fund.flio.utils.ViewUtils.readAssetJson;


public class EventFragment extends BaseFragment<FragmentEventBinding, EventViewModel> {

    private TabLayout mTabLayout;

    @Inject
    EventAdapter mEventAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_event;
    }

    @Override
    public EventViewModel getViewModel() {
        return getViewModelProvider().get(EventViewModel.class);
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
        mTabLayout = getViewDataBinding().tabs;
        mTabLayout.addTab(mTabLayout.newTab().setText("전체"));
        mTabLayout.addTab(mTabLayout.newTab().setText("세미나/강좌"));
        mTabLayout.addTab(mTabLayout.newTab().setText("청음회"));
        mTabLayout.addTab(mTabLayout.newTab().setText("구인구직"));
        mTabLayout.addTab(mTabLayout.newTab().setText("기타"));

        getViewDataBinding().news.setAdapter(mEventAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getBaseActivity(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getBaseActivity(), R.drawable.recycler_divider_vertical)));
        getViewDataBinding().news.addItemDecoration(dividerItemDecoration);
        ArrayList<Event> dummyEvents = new Gson().fromJson(readAssetJson(getContext(), "events.json"), new TypeToken<List<Event>>() {
        }.getType());
        mEventAdapter.addItems(dummyEvents);
    }

}
