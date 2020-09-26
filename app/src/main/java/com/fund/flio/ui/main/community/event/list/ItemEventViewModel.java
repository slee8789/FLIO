package com.fund.flio.ui.main.community.event.list;


import android.view.View;

import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.model.Event;
import com.fund.flio.data.model.News;
import com.fund.flio.ui.main.MainActivity;
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

    public void onItemClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_community_to_nav_event_detail);
    }

}
