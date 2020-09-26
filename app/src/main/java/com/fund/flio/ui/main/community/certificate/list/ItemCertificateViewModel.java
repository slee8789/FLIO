package com.fund.flio.ui.main.community.certificate.list;


import android.view.View;

import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.fund.flio.R;
import com.fund.flio.data.model.Certificate;
import com.fund.flio.data.model.News;
import com.fund.flio.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

public class ItemCertificateViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ItemCertificateViewModel(Certificate certificate) {
//        Logger.d("ItemCertificateViewModel " + certificate);
        title.set(certificate.getTitle());
        date.set(certificate.getDate());
        description.set(certificate.getDescription());

    }

    public void onItemClick(View v) {
        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_community_to_nav_certificate_detail);
    }

}
