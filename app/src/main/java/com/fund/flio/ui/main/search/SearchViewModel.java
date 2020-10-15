package com.fund.flio.ui.main.search;


import android.view.SearchEvent;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.SearchResult;
import com.fund.flio.data.model.body.SearchBody;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

import gun0912.tedkeyboardobserver.TedRxKeyboardObserver;

public class SearchViewModel extends BaseViewModel {

    public ObservableField<String> searchText = new ObservableField<>();
    private final ObservableBoolean isKeyboardShow = new ObservableBoolean(false);
    private SearchResult searchResult = new SearchResult();
    private MutableLiveData<List<SearchResult>> searchResults = new MutableLiveData<>();

    public ObservableBoolean getIsKeyboardShow() {
        return isKeyboardShow;
    }

    public MutableLiveData<List<SearchResult>> getSearchResults() {
        return searchResults;
    }

    public void setIsKeyboardShow(boolean isKeyboardShow) {
        this.isKeyboardShow.set(isKeyboardShow);
    }

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        getAllSearchResults();
    }

    public void onSearchClear(View v) {
        searchText.set("");
    }

    public void getAllSearchResults() {
        getCompositeDisposable2().add(getDataManager().getSearchResults()
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe(result -> searchResults.setValue(result)));
    }

    public void searchKeyword(String request) {
        getCompositeDisposable().add(getDataManager().searchKeyword(new SearchBody(request))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe());
    }

    public void onDataInsert(String request) {
        searchResult.setTitle(request);
        searchResult.setDate(System.currentTimeMillis());
        getCompositeDisposable2().add(getDataManager().insertSearchResult(searchResult)
                .concatMap(result -> getDataManager().getSearchResults())
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe(result -> searchResults.setValue(result)));
    }

    public void onDataDelete(SearchResult request) {
        getCompositeDisposable2().add(getDataManager().deleteSearchResult(request)
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe());
    }

    public void onDataDeleteAll(View v) {
        getCompositeDisposable2().add(getDataManager().deleteAll()
                .subscribeOn(getSchedulerProvider().io2())
                .observeOn(getSchedulerProvider().ui2())
                .subscribe());
    }
}
