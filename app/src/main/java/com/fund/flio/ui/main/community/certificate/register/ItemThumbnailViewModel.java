package com.fund.flio.ui.main.community.certificate.register;

import android.net.Uri;
import android.view.View;

import androidx.databinding.ObservableField;

public class ItemThumbnailViewModel {

    public  Uri mUri;

    public ObservableField<Uri> thumbnailUri = new ObservableField<>();

    public ItemThumbnailViewModel(Uri uri) {
        mUri = uri;
        thumbnailUri.set(uri);
    }

    public void onItemClick(View v) {

    }
}
