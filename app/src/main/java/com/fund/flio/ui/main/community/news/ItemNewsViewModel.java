package com.fund.flio.ui.main.community.news;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Message;
import com.fund.flio.data.model.News;
import com.orhanobut.logger.Logger;

public class ItemNewsViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemNewsViewModel(News news) {
        Logger.d("ItemNewsViewModel " + news);
        title.set(news.getTitle());
        date.set(news.getDate());
        description.set(news.getDescription());

    }

}
