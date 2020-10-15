package com.fund.flio.ui.main.community.news;


import androidx.lifecycle.MutableLiveData;

import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.Search;
import com.fund.flio.data.model.body.SearchBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;

import java.util.List;

public class NewsViewModel extends BaseViewModel {

    private MutableLiveData<List<Search>> searchs = new MutableLiveData<>();

    public MutableLiveData<List<Search>> getSearchs() {
        return searchs;
    }

    public NewsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
    }

    public void searchKeyword(String keyword) {
        getCompositeDisposable().add(getDataManager().searchKeyword(new SearchBody(keyword))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(keywords -> {
                    if (keywords.isSuccessful()) {
                        searchs.setValue(keywords.body().getSearchs());
                    }
                }));
    }

}
