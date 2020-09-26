package com.fund.flio.ui.main.community.certificate.list;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

}