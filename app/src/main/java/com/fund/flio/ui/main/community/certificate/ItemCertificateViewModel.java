package com.fund.flio.ui.main.community.certificate;


import androidx.databinding.ObservableField;

import com.fund.flio.data.model.Certificate;
import com.fund.flio.data.model.News;
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

}
