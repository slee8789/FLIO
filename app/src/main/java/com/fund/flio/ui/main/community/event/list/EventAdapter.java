package com.fund.flio.ui.main.community.event.list;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Chat;
import com.fund.flio.data.model.Event;
import com.fund.flio.databinding.ItemEventBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.message.chat.detail.ChatAdapter;

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

    public void setItems(List<Event> events) {
        final EventAdapter.EventDiffCallback diffCallback = new EventAdapter.EventDiffCallback(this.events, events);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.events.clear();
        this.events.addAll(events);
        diffResult.dispatchUpdatesTo(this);
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

    private static class EventDiffCallback extends DiffUtil.Callback {
        private final List<Event> oldEvents;
        private final List<Event> newEvents;

        public EventDiffCallback(List<Event> oldEvents, List<Event> newEvents) {
            this.oldEvents = oldEvents;
            this.newEvents = newEvents;
        }

        @Override
        public int getOldListSize() {
            return oldEvents.size();
        }

        @Override
        public int getNewListSize() {
            return newEvents.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldEvents.get(oldItemPosition).getEventId() == newEvents.get(newItemPosition).getEventId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Event oldChat = oldEvents.get(oldItemPosition);
            final Event newChat = newEvents.get(newItemPosition);
            return oldChat.equals(newChat);
        }

    }

}