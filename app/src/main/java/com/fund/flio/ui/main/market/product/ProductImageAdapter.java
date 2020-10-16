package com.fund.flio.ui.main.market.product;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.databinding.ItemProductImageBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.market.ItemProductViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductImageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<String> products;


    public ProductImageAdapter(ArrayList<String> products) {
        this.products = products;
    }

    private ProductViewModel productViewModel;

    public void setProductViewModel(ProductViewModel productViewModel) {
        this.productViewModel = productViewModel;
    }

    public void addItems(List<String> banners) {
        this.products.addAll(banners);
        notifyDataSetChanged();
    }

    public void setItems(List<String> products) {
        Logger.d("setItems " + products.size());
        final ProductImageAdapter.ProductDiffCallback diffCallback = new ProductImageAdapter.ProductDiffCallback(this.products, products);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.products.clear();
        this.products.addAll(products);
        Logger.d("setItems after " + this.products.size());
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductImageBinding productBinding = ItemProductImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends BaseViewHolder {

        private ItemProductImageBinding productBinding;

        public ProductViewHolder(ItemProductImageBinding binding) {
            super(binding.getRoot());
            this.productBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final String product = products.get(position);
            ItemProductImageViewModel itemProductImageViewModel = new ItemProductImageViewModel(product);
            productBinding.setItemViewModel(itemProductImageViewModel);
            productBinding.setViewModel(productViewModel);
        }

    }

    private static class ProductDiffCallback extends DiffUtil.Callback {
        private final List<String> oldProducts;
        private final List<String> newProducts;

        public ProductDiffCallback(List<String> oldProducts, List<String> newProducts) {
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
            final String oldChatRoom = oldProducts.get(oldItemPosition);
            final String newChatRoom = newProducts.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}