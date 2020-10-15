package com.fund.flio.ui.main.community.certificate.list;


import android.view.View;

import androidx.databinding.ObservableBoolean;
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
    public ObservableField<String> tag1 = new ObservableField<>();
    public ObservableField<String> tag2 = new ObservableField<>();
    public ObservableField<String> tag3 = new ObservableField<>();
    public ObservableBoolean tag_visible_1 = new ObservableBoolean(false);
    public ObservableBoolean tag_visible_2 = new ObservableBoolean(false);
    public ObservableBoolean tag_visible_3 = new ObservableBoolean(false);

    public ItemCertificateViewModel(Certificate certificate) {
//        Logger.d("ItemCertificateViewModel " + certificate);
        title.set(certificate.getTitle());
        date.set(certificate.getDate());
        description.set(certificate.getDescription());
        imageUrl.set(certificate.getImageUrl());

        String[] tags = certificate.getTag().split(",");
        switch (tags.length) {
            case 1:
                tag1.set(tags[0]);
                tag_visible_1.set(true);
                break;
            case 2:
                tag1.set(tags[0]);
                tag2.set(tags[1]);
                tag_visible_1.set(true);
                tag_visible_2.set(true);
                break;
            case 3:
                tag1.set(tags[0]);
                tag2.set(tags[1]);
                tag3.set(tags[2]);
                tag_visible_1.set(true);
                tag_visible_2.set(true);
                tag_visible_3.set(true);
                break;
            default:
        }

    }

    public void onItemClick(View v) {
//        Navigation.findNavController((MainActivity) v.getContext(), R.id.fragment_container).navigate(R.id.action_nav_community_to_nav_certificate_detail);
    }

}
