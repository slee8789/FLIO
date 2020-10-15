package com.fund.flio.ui.main.community.news;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.ChatRoom;
import com.fund.flio.data.model.News;
import com.fund.flio.data.model.Search;
import com.fund.flio.databinding.ItemNewsBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.message.chat.list.ChatListAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Search> newses;


    public NewsAdapter(ArrayList<Search> newses) {
        this.newses = newses;
    }

    public void addItems(List<Search> newses) {
        this.newses.addAll(newses);
        notifyDataSetChanged();
    }

    public void setItems(List<Search> chatRooms) {
        final NewsAdapter.NewsDiffCallback diffCallback = new NewsAdapter.NewsDiffCallback(this.newses, chatRooms);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.newses.clear();
        this.newses.addAll(chatRooms);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding newsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(newsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class NewsViewHolder extends BaseViewHolder {

        private ItemNewsBinding newsBinding;

        public NewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.newsBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Search news = newses.get(position);
            ItemNewsViewModel newsViewModel = new ItemNewsViewModel(news);
            newsBinding.setViewModel(newsViewModel);
        }

    }

    private static class NewsDiffCallback extends DiffUtil.Callback {
        private final List<Search> oldSearchs;
        private final List<Search> newSearchs;

        public NewsDiffCallback(List<Search> oldSearchs, List<Search> newSearchs) {
            this.oldSearchs = oldSearchs;
            this.newSearchs = newSearchs;
        }

        @Override
        public int getOldListSize() {
            return oldSearchs.size();
        }

        @Override
        public int getNewListSize() {
            return newSearchs.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldSearchs.get(oldItemPosition).getTargetUrl().equals(newSearchs.get(newItemPosition).getTargetUrl());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Search oldSearch = oldSearchs.get(oldItemPosition);
            final Search newSearch = newSearchs.get(newItemPosition);
            return oldSearch.equals(newSearch);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}