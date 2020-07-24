package com.fund.flio.ui.main.home;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Banner;
import com.fund.flio.databinding.ItemBannerBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Banner> banners;


    public BannerAdapter(ArrayList<Banner> banners) {
        this.banners = banners;
    }

    public void addItems(List<Banner> banners) {
        this.banners.addAll(banners);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBannerBinding bannerBinding = ItemBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BannerViewHolder(bannerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class BannerViewHolder extends BaseViewHolder {

        private ItemBannerBinding bannerBinding;

        public BannerViewHolder(ItemBannerBinding binding) {
            super(binding.getRoot());
            this.bannerBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Banner banner = banners.get(position);
            ItemBannerViewModel bannerViewModel = new ItemBannerViewModel(banner);
            bannerBinding.setViewModel(bannerViewModel);
        }

    }

}