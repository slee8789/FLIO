package com.fund.flio.ui.main.market;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Product> products;


    public ProductAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    public void addItems(List<Product> banners) {
        this.products.addAll(banners);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding productBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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

        private ItemProductBinding productBinding;

        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.productBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Product product = products.get(position);
            ItemProductViewModel productViewModel = new ItemProductViewModel(itemView, productBinding, product);
            productBinding.setViewModel(productViewModel);
        }

    }

}