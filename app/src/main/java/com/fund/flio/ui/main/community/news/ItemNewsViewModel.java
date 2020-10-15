package com.fund.flio.ui.main.community.news;


import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.model.News;
import com.fund.flio.data.model.Search;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.icu.lang.UProperty.INT_START;

public class ItemNewsViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> targetUrl = new ObservableField<>();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

    public ItemNewsViewModel(Search news) {
        Logger.d("ItemNewsViewModel " + news.getRegDate().substring(0, 10));
        title.set(news.getTitle());
        date.set(news.getRegDate().substring(0, 10));
        description.set(news.getContent().replaceAll("<HS>", "").replaceAll("</HE>", ""));
        imageUrl.set("http://flio.iptime.org:8080/image/" + news.getImageUrl());
        targetUrl.set(news.getTargetUrl());

    }

    public void showLink(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        if (!targetUrl.get().startsWith("http://") && !targetUrl.get().startsWith("https://")) {
            targetUrl.set("http://" + targetUrl);
        }
        i.setData(Uri.parse(targetUrl.get()));
        (v.getContext()).startActivity(i);
    }
}
