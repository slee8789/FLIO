package com.fund.flio.ui.main.market.register;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.fund.flio.R;
import com.fund.flio.data.model.Product;
import com.fund.flio.ui.main.MainActivity;

import java.io.File;

public class ItemThumbnailViewModel {

    public ObservableField<Uri> thumbnailUri = new ObservableField<>();

    public ItemThumbnailViewModel(Uri uri) {
        thumbnailUri.set(uri);
    }

    public void onItemClick(View v) {

    }

    public void onItemDeleteClick(View v) {

    }

}
