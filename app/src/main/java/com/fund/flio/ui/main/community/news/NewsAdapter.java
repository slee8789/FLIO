package com.fund.flio.ui.main.community.news;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.News;
import com.fund.flio.databinding.ItemNewsBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.community.news.ItemNewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<News> newses;


    public NewsAdapter(ArrayList<News> newses) {
        this.newses = newses;
    }

    public void addItems(List<News> newses) {
        this.newses.addAll(newses);
        notifyDataSetChanged();
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
            final News news = newses.get(position);
            ItemNewsViewModel newsViewModel = new ItemNewsViewModel(news);
            newsBinding.setViewModel(newsViewModel);
        }

    }

}