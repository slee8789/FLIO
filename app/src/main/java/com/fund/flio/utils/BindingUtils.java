package com.fund.flio.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fund.flio.R;
import com.fund.flio.data.model.Chat;
import com.fund.flio.ui.main.message.chat.detail.ChatAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Objects;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("enabled")
    public static void enabled(ViewGroup viewGroup, boolean enable) {
        viewGroup.setEnabled(enable);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.color.grayCE)
                .into(imageView);
    }

    @BindingAdapter({"imageUrlCircle"})
    public static void loadImageCircle(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.color.grayCE)
                .into(imageView);
    }

    @BindingAdapter({"imageUrlRoundCorner"})
    public static void loadImageRoundCorner(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.color.grayCE)
                .into(imageView);
    }

    @BindingAdapter({"imageUrlRoundCornerOnlyTop"})
    public static void loadImageRoundCornerOnlyTop(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new GranularRoundedCorners(14, 14, 0, 0)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.color.grayCE)
                .into(imageView);
    }

    @BindingAdapter({"setBackground"})
    public static void setBackground(View view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        view.setBackground(resource);
                    }
                });
    }

    @BindingAdapter("listData")
    public static void bindRecyclerView(RecyclerView recyclerView, List<Chat> chats) {
        Logger.d("bindRecyclerView " + chats);
        ((ChatAdapter) Objects.requireNonNull(recyclerView.getAdapter())).setItems(chats);
    }

}
