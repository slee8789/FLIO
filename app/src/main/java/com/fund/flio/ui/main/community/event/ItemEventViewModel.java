package com.fund.flio.ui.main.community.event;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Event;
import com.fund.flio.data.model.News;
import com.orhanobut.logger.Logger;

public class ItemEventViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemEventViewModel(Event event) {
        Logger.d("ItemEventViewModel " + event);
        title.set(event.getTitle());
        date.set(event.getDate());
        description.set(event.getDescription());

    }

}
