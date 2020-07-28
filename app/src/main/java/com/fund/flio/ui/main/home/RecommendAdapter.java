package com.fund.flio.ui.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Recommend;
import com.fund.flio.databinding.ItemRecommendBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.List;

import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_EMPTY;
import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_NORMAL;

public class RecommendAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<Recommend> recommends;

    public RecommendAdapter(List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public void addItems(List<Recommend> recommends) {
        this.recommends.addAll(recommends);
        notifyDataSetChanged();
    }

    public void clearItems() {
        recommends.clear();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemRecommendBinding recommendBinding = ItemRecommendBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecommendViewHolder(recommendBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (recommends != null && recommends.size() > 0) {
            return recommends.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (recommends != null && !recommends.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public class RecommendViewHolder extends BaseViewHolder {

        private ItemRecommendBinding binding;

        public RecommendViewHolder(ItemRecommendBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Recommend recommend = recommends.get(position);
            ItemRecommendViewModel recommendViewModel = new ItemRecommendViewModel(recommend);
            binding.setViewModel(recommendViewModel);
        }
    }
}
