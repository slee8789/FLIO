package com.fund.flio.ui.main.community.event.register;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.List;

import gun0912.tedimagepicker.builder.TedRxImagePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.fund.flio.utils.CommonUtils.getRealPathFromUri;

public class EventRegisterViewModel extends BaseViewModel {

    private Context mContext;

    private MutableLiveData<List<Uri>> mThumbnailUris = new MutableLiveData<>();

    public MutableLiveData<List<Uri>> getThumbnailUris() {
        return mThumbnailUris;
    }

    public MutableLiveData<String> tag = new MutableLiveData<>();
    public ObservableField<String> tagCount = new ObservableField<>();
    public ObservableField<String> imageCount = new ObservableField<>(String.valueOf(0));

    public EventRegisterViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        mContext = context;
    }

    public void addImage(View v) {
        getCompositeDisposable2().add(TedRxImagePicker
                .with(v.getContext())
                .max(10, getResourceProvider().getString(R.string.message_max_count))
                .showCameraTile(true)
                .startMultiImage()
                .subscribe(uriList -> {
                    imageCount.set(String.valueOf(uriList.size()));
                    mThumbnailUris.setValue(uriList);
                }));

    }


    public void registerProduct() {
        MultipartBody.Part[] imgUrls = new MultipartBody.Part[mThumbnailUris.getValue().size()];

        for (int i = 0; i < mThumbnailUris.getValue().size(); i++) {
            File file = new File(getRealPathFromUri(mContext, mThumbnailUris.getValue().get(i)));
            RequestBody imgBody = RequestBody.create(MediaType.parse("image/*"), file);
            imgUrls[i] = MultipartBody.Part.createFormData("imgList", file.getName(), imgBody);
        }

        RequestBody productName = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody content = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody status = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody saleYn = RequestBody.create(MediaType.parse("text/plain"), "Y");
        RequestBody classification = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody tag = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");

        RequestBody displayYn = RequestBody.create(MediaType.parse("text/plain"), "Y");
        RequestBody useDate = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody purchaseKind = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody purchasePrice = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody boxYn = RequestBody.create(MediaType.parse("text/plain"), "Y");
        RequestBody brand = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody purpose = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody modelNo = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody serialNo = RequestBody.create(MediaType.parse("text/plain"), "Y");
        RequestBody repairYn = RequestBody.create(MediaType.parse("text/plain"), "N");
        RequestBody productRelatedUrl = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody uid = RequestBody.create(MediaType.parse("text/plain"), getDataManager().getUserId());

        getCompositeDisposable().add(getDataManager().insertProduct(productName, title, content, status, saleYn, classification, tag, imgUrls, displayYn, useDate, purchaseKind, purchasePrice, boxYn, brand, purpose, modelNo, serialNo, repairYn, productRelatedUrl, uid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    Logger.i("multipart success");
                }, onError -> Logger.e("multipart error " + onError.getMessage())));

    }

    public void onKeywordRegisterClick(View v) {
//        if (keywords.getValue().size() == 10) {
//            showSnackBar(v, getResourceProvider().getString(R.string.message_max_count));
//            return;
//        }
//        mKeyword.setKeyword(keyword.getValue());
//        getCompositeDisposable2().add(getDataManager().insertKeyword(mKeyword)
//                .doOnNext(result -> Logger.i("insert result " + result))
//                .concatMap(result -> getDataManager().getKeywords())
//                .subscribeOn(getSchedulerProvider().io2())
//                .observeOn(getSchedulerProvider().ui2())
//                .subscribe(result -> {
//                    keywords.setValue(result);
//                    keywordCount.set(keywords.getValue().size() + "/10");
//                }, onError -> showSnackBar(v, getResourceProvider().getString(R.string.message_already_registered))));
//
//        keyword.setValue("");
    }

    public void onItemDeleteClick(View v, Uri uri) {
        mThumbnailUris.setValue(Stream.of(mThumbnailUris.getValue()).filterNot(thumbnailUri -> thumbnailUri == uri).collect(Collectors.toList()));
        imageCount.set(String.valueOf(mThumbnailUris.getValue().size()));
    }


}
