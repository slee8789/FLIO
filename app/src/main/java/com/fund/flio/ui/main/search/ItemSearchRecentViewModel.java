package com.fund.flio.ui.main.search;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.SearchResult;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

public class ItemSearchRecentViewModel {

    public ObservableField<String> title = new ObservableField<>();

    public SearchResult mSearchResult;

    public ItemSearchRecentViewModel(SearchResult searchResult) {
        Logger.d("ItemSearchRecentViewModel " + searchResult);
        mSearchResult = searchResult;
        this.title.set(searchResult.getTitle());

    }


}
