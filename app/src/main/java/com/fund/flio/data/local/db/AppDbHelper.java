package com.fund.flio.data.local.db;

import com.fund.flio.data.model.SearchResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<SearchResult>> getSearchResults() {
        return mAppDatabase.searchResultDao().loadAll();
    }

    @Override
    public Observable<Boolean> insertSearchResult(SearchResult searchResult) {
        return Observable.fromCallable(() -> {
            mAppDatabase.searchResultDao().insert(searchResult);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteSearchResult(SearchResult searchResult) {
        return Observable.fromCallable(() -> {
            mAppDatabase.searchResultDao().delete(searchResult);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteAll() {
        return Observable.fromCallable(() -> {
            mAppDatabase.searchResultDao().deleteAll();
            return true;
        });
    }
}
