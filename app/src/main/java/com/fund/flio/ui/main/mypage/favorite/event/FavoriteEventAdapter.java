package com.fund.flio.ui.main.mypage.favorite.event;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductFavoriteBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.market.ItemProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteEventAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Product> products;


    public FavoriteEventAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    public void setItems(List<Product> products) {
        final FavoriteEventAdapter.ProductDiffCallback diffCallback = new FavoriteEventAdapter.ProductDiffCallback(this.products, products);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.products.clear();
        this.products.addAll(products);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductFavoriteBinding productBinding = ItemProductFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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

        private ItemProductFavoriteBinding productBinding;

        public ProductViewHolder(ItemProductFavoriteBinding binding) {
            super(binding.getRoot());
            this.productBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Product product = products.get(position);
            ItemProductViewModel productViewModel = new ItemProductViewModel(itemView, productBinding, product);
            productBinding.setItemViewModel(productViewModel);
        }

    }

    private static class ProductDiffCallback extends DiffUtil.Callback {
        private final List<Product> oldProducts;
        private final List<Product> newProducts;

        public ProductDiffCallback(List<Product> oldProducts, List<Product> newProducts) {
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
            return oldProducts.get(oldItemPosition).getProductId() == newProducts.get(newItemPosition).getProductId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Product oldChatRoom = oldProducts.get(oldItemPosition);
            final Product newChatRoom = newProducts.get(newItemPosition);
            return oldChatRoom.equals(newChatRoom);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }

}