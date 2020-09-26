package com.fund.flio.ui.main.market.register;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.model.ChatRoom;
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

public class ProductRegisterViewModel extends BaseViewModel {

    private Context mContext;

    private MutableLiveData<List<Uri>> mThumbnailUris = new MutableLiveData<>();

    public MutableLiveData<List<Uri>> getThumbnailUris() {
        return mThumbnailUris;
    }

    public ObservableField<String> imageCount = new ObservableField<>("0");

    public ProductRegisterViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        mContext = context;
    }

    public void addImage(View v) {
        getCompositeDisposable2().add(TedRxImagePicker
                .with(v.getContext())
                .max(10, getResourceProvider().getString(R.string.image_max_count_message))
                .showCameraTile(true)
//                .cameraTileBackground(getResourceProvider().getColor(R.color.purple))
                .startMultiImage()
                .subscribe(uriList -> mThumbnailUris.setValue(uriList)));

    }


    public void registerProduct() {
        MultipartBody.Part[] imgUrls = new MultipartBody.Part[mThumbnailUris.getValue().size()];

        for (int i = 0; i < mThumbnailUris.getValue().size(); i++) {
            File file = new File(getRealPathFromUri(mContext, mThumbnailUris.getValue().get(i)));
            RequestBody imgBody = RequestBody.create(MediaType.parse("image/*"), file);
            imgUrls[i] = MultipartBody.Part.createFormData("imgList", file.getName(), imgBody);
        }

        RequestBody boardKind = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        RequestBody boardTitle = RequestBody.create(MediaType.parse("text/plain"), "hi");

        getCompositeDisposable().add(getDataManager().testImageUpload(imgUrls, boardKind, boardTitle)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    Logger.i("multipart success");
                }, onError -> Logger.e("multipart error " + onError.getMessage())));

    }

    private void requestUploadSurvey() {
//        File propertyImageFile = new File(surveyModel.getPropertyImagePath());
//
//        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"),
//                propertyImageFile);
//        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("PropertyImage",
//                propertyImageFile.getName(),
//                propertyImage);
//
//        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[surveyModel.getPicturesList()
//                .size()];
//
//        for (int index = 0; index < surveyModel.getPicturesList().size(); index++) {
//
//            File file = new File(surveyModel.getPicturesList().get(index).getImagePath());
//            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file);
//        }
//            surveyImagesParts[index] = MultipartBody.Part.createFormData("SurveyImage", file.getName(), surveyBody);


    }


}
