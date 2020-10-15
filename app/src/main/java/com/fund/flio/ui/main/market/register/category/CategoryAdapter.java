package com.fund.flio.ui.main.market.register.category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.databinding.ItemCategoryStringBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.market.register.ProductRegisterViewModel;
import com.fund.flio.ui.main.mypage.sell.selling.buyer.list.BuyerListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<String> categories;

    private ProductRegisterViewModel productRegisterViewModel;

    public void setBuyerListViewModel(ProductRegisterViewModel productRegisterViewModel) {
        this.productRegisterViewModel = productRegisterViewModel;
    }

    public CategoryAdapter(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setItems(List<String> categories) {
        final DiffCallback diffCallback = new DiffCallback(this.categories, categories);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.categories.clear();
        this.categories.addAll(categories);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryStringBinding productBinding = ItemCategoryStringBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoriViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriViewHolder extends BaseViewHolder {

        private ItemCategoryStringBinding categoryBinding;

        public CategoriViewHolder(ItemCategoryStringBinding binding) {
            super(binding.getRoot());
            this.categoryBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final String category = categories.get(position);
            ItemCategoryViewModel categoryViewModel = new ItemCategoryViewModel(position, category);
            categoryBinding.setItemViewModel(categoryViewModel);
            categoryBinding.setViewModel(productRegisterViewModel);
        }

    }

    private static class DiffCallback extends DiffUtil.Callback {
        private final List<String> oldCategories;
        private final List<String> newCategories;

        public DiffCallback(List<String> oldCategories, List<String> newCategories) {
            this.oldCategories = oldCategories;
            this.newCategories = newCategories;
        }

        @Override
        public int getOldListSize() {
            return oldCategories.size();
        }

        @Override
        public int getNewListSize() {
            return newCategories.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCategories.get(oldItemPosition) == newCategories.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final String oldCategories = this.oldCategories.get(oldItemPosition);
            final String newCategories = this.newCategories.get(newItemPosition);
            return oldCategories.equals(newCategories);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}