package com.fund.flio.ui.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemProductHomeBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.market.ItemProductViewModel;

import java.util.List;

import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_EMPTY;
import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_NORMAL;

public class ProductSmallAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<Product> products;

    public ProductSmallAdapter(List<Product> products) {
        this.products = products;
    }

    public void addItems(List<Product> recommends) {
        this.products.addAll(recommends);
        notifyDataSetChanged();
    }

    public void clearItems() {
        products.clear();
    }

    public void setItems(List<Product> products) {
        final ProductSmallAdapter.ProductDiffCallback diffCallback = new ProductSmallAdapter.ProductDiffCallback(this.products, products);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.products.clear();
        this.products.addAll(products);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemProductHomeBinding recommendBinding = ItemProductHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecommendViewHolder(recommendBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (products != null && !products.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public class RecommendViewHolder extends BaseViewHolder {

        private ItemProductHomeBinding binding;

        public RecommendViewHolder(ItemProductHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Product product = products.get(position);
            ItemProductViewModel recommendViewModel = new ItemProductViewModel(itemView, binding, product);
            binding.setViewModel(recommendViewModel);
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
