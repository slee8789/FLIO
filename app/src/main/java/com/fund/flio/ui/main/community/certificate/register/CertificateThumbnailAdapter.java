package com.fund.flio.ui.main.community.certificate.register;


import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.databinding.ItemThumbnailBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CertificateThumbnailAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Uri> uries;
    private CertificateRegisterViewModel certificateRegisterViewModel;

    public void setCertificateRegisterViewModel(CertificateRegisterViewModel certificateRegisterViewModel) {
        this.certificateRegisterViewModel = certificateRegisterViewModel;
    }

    public CertificateThumbnailAdapter(ArrayList<Uri> uries) {
        this.uries = uries;
    }

    public void setItems(List<Uri> uries) {
        final CertificateThumbnailAdapter.UriDiffCallback diffCallback = new CertificateThumbnailAdapter.UriDiffCallback(this.uries, uries);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.uries.clear();
        this.uries.addAll(uries);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemThumbnailBinding productBinding = ItemThumbnailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return uries.size();
    }

    public class ProductViewHolder extends BaseViewHolder {

        private ItemThumbnailBinding thumbnailBinding;

        public ProductViewHolder(ItemThumbnailBinding binding) {
            super(binding.getRoot());
            this.thumbnailBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Uri uri = uries.get(position);
            ItemThumbnailViewModel thumbnailViewModel = new ItemThumbnailViewModel(uri);
//            thumbnailBinding.setItemViewModel(thumbnailViewModel);
//            thumbnailBinding.setViewModel(certificateRegisterViewModel);
        }

    }

    private static class UriDiffCallback extends DiffUtil.Callback {
        private final List<Uri> oldUries;
        private final List<Uri> newUries;

        public UriDiffCallback(List<Uri> oldUries, List<Uri> newUries) {
            this.oldUries = oldUries;
            this.newUries = newUries;
        }

        @Override
        public int getOldListSize() {
            return oldUries.size();
        }

        @Override
        public int getNewListSize() {
            return newUries.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldUries.get(oldItemPosition).getPath() == newUries.get(newItemPosition).getPath();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Uri oldUri = oldUries.get(oldItemPosition);
            final Uri newUri = newUries.get(newItemPosition);
            return oldUri.equals(newUri);
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}