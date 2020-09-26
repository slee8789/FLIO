package com.fund.flio.data.local.db;

import com.fund.flio.data.model.Keyword;
import com.fund.flio.data.model.SearchResult;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<List<SearchResult>> getSearchResults();

    Observable<Boolean> insertSearchResult(SearchResult searchResult);

    Observable<Boolean> deleteSearchResult(SearchResult searchResult);

    Observable<Boolean> deleteAll();

    Observable<List<Keyword>> getKeywords();

    Observable<Keyword> isExist(String keyword);

    Observable<Boolean> insertKeyword(Keyword keyword);

    Observable<Boolean> deleteKeyword(Keyword keyword);
}
