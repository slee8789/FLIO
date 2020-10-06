package com.fund.flio.ui.main.community.certificate.list;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fund.flio.data.model.Certificate;
import com.fund.flio.databinding.ItemCertificateBinding;
import com.fund.flio.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CertificateAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<Certificate> certificates;

    public CertificateAdapter(ArrayList<Certificate> certificates) {
        this.certificates = certificates;
    }

    public void addItems(List<Certificate> newses) {
        this.certificates.addAll(newses);
        notifyDataSetChanged();
    }

    public void setItems(List<Certificate> certificates) {
        final CertificateAdapter.CertificateDiffCallback diffCallback = new CertificateAdapter.CertificateDiffCallback(this.certificates, certificates);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.certificates.clear();
        this.certificates.addAll(certificates);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCertificateBinding certificateBinding = ItemCertificateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CertificateViewHolder(certificateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return certificates.size();
    }

    public class CertificateViewHolder extends BaseViewHolder {

        private ItemCertificateBinding certificateBinding;

        public CertificateViewHolder(ItemCertificateBinding binding) {
            super(binding.getRoot());
            this.certificateBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Certificate certificate = certificates.get(position);
            ItemCertificateViewModel certificateViewModel = new ItemCertificateViewModel(certificate);
            certificateBinding.setViewModel(certificateViewModel);
        }

    }

    private static class CertificateDiffCallback extends DiffUtil.Callback {
        private final List<Certificate> oldCertificates;
        private final List<Certificate> newCertificates;

        public CertificateDiffCallback(List<Certificate> oldCertificates, List<Certificate> newCertificates) {
            this.oldCertificates = oldCertificates;
            this.newCertificates = newCertificates;
        }

        @Override
        public int getOldListSize() {
            return oldCertificates.size();
        }

        @Override
        public int getNewListSize() {
            return newCertificates.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldCertificates.get(oldItemPosition).getCertificateId() == newCertificates.get(newItemPosition).getCertificateId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final Certificate oldChat = oldCertificates.get(oldItemPosition);
            final Certificate newChat = newCertificates.get(newItemPosition);
            return oldChat.equals(newChat);
        }

    }

}