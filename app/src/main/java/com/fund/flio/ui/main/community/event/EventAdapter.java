package com.fund.flio.ui.main.community.event;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Event;
import com.fund.flio.databinding.ItemEventBinding;
import com.fund.flio.databinding.ItemNewsBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Event> events;


    public EventAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    public void addItems(List<Event> newses) {
        this.events.addAll(newses);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEventBinding eventBinding = ItemEventBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventViewHolder(eventBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventViewHolder extends BaseViewHolder {

        private ItemEventBinding eventBinding;

        public EventViewHolder(ItemEventBinding binding) {
            super(binding.getRoot());
            this.eventBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Event event = events.get(position);
            ItemEventViewModel eventViewModel = new ItemEventViewModel(event);
            eventBinding.setViewModel(eventViewModel);
        }

    }

}