package com.fund.flio.ui.main.home;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Banner;
import com.fund.flio.databinding.ItemBannerImageBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class BannerImageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Banner> banners;


    public BannerImageAdapter(ArrayList<Banner> banners) {
        this.banners = banners;
    }

    public void addItems(List<Banner> banners) {
        this.banners.addAll(banners);
        notifyDataSetChanged();
    }

    public void setItems(List<Banner> products) {
        Logger.d("setItems " + products.size());
        final BannerImageAdapter.ProductDiffCallback diffCallback = new BannerImageAdapter.ProductDiffCallback(this.banners, products);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.banners.clear();
        this.banners.addAll(products);
        Logger.d("setItems after " + this.banners.size());
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBannerImageBinding bannerImageBinding = ItemBannerImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(bannerImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class ProductViewHolder extends BaseViewHolder {

        private ItemBannerImageBinding productBinding;

        public ProductViewHolder(ItemBannerImageBinding binding) {
            super(binding.getRoot());
            this.productBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Banner product = banners.get(position);
            ItemBannerImageViewModel productViewModel = new ItemBannerImageViewModel(product);
            productBinding.setItemViewModel(productViewModel);
        }

    }

    private static class ProductDiffCallback extends DiffUtil.Callback {
        private final List<Banner> oldProducts;
        private final List<Banner> newProducts;

        public ProductDiffCallback(List<Banner> oldProducts, List<Banner> newProducts) {
            this.oldProducts = oldProducts;
            this.newProducts = newProducts;
        }

        @Override
        public int getOldListSize() {
            return oldProducts.size();
        }

        @Override
        public int getNewListSize() {
            return newProducts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldProducts.get(oldItemPosition) == newProducts.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Banner oldChatRoom = oldProducts.get(oldItemPosition);
            final Banner newChatRoom = newProducts.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}