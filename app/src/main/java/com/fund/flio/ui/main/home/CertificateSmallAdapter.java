package com.fund.flio.ui.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Certificate;
import com.fund.flio.data.model.Product;
import com.fund.flio.databinding.ItemCertificateBinding;
import com.fund.flio.databinding.ItemCertificateHomeBinding;
import com.fund.flio.ui.base.BaseViewHolder;
import com.fund.flio.ui.main.community.certificate.list.ItemCertificateViewModel;

import java.util.List;

import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_EMPTY;
import static com.fund.flio.ui.base.BaseViewHolder.VIEW_TYPE_NORMAL;

public class CertificateSmallAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<Certificate> recommends;

    public CertificateSmallAdapter(List<Certificate> recommends) {
        this.recommends = recommends;
    }

    public void addItems(List<Certificate> recommends) {
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
        ItemCertificateHomeBinding certificatedBinding = ItemCertificateHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CertificatedViewHolder(certificatedBinding);
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

    public class CertificatedViewHolder extends BaseViewHolder {

        private ItemCertificateHomeBinding binding;

        public CertificatedViewHolder(ItemCertificateHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Certificate recommend = recommends.get(position);
            ItemCertificateViewModel recommendViewModel = new ItemCertificateViewModel(recommend);
            binding.setViewModel(recommendViewModel);
        }
    }
}
