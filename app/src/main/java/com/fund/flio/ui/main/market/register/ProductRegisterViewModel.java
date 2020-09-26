package com.fund.flio.ui.main.market.register;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import androidx.databinding.ObservableField;

import com.fund.flio.data.DataManager;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.orhanobut.logger.Logger;


import gun0912.tedimagepicker.builder.TedRxImagePicker;

public class ProductRegisterViewModel extends BaseViewModel {

    private Context mContext;
    public ObservableField<String> imageCount = new ObservableField<>("0");

    public ProductRegisterViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        mContext = context;
    }

    public void addImage(View v) {
        getCompositeDisposable2().add(TedRxImagePicker.with(v.getContext())
                .startMultiImage()
                .subscribe(uriList -> {
                    Logger.d("addImage " + uriList.get(0));
//                    Uri uri = Uri.fromFile(new File(media.getPath()));
//                    Glide.with(getContext()).
//                            load(uri).
//                            thumbnail(0.1f).
//                            into(viewHolderImage.recordedMedia);
                }));

    }



    public void registerProduct() {
//        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("imgList", propertyImageFile.getName(), propertyImage);
//
//        getCompositeDisposable().add(getDataManager().testImageUpload()
//                .subscribe());


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
//            surveyImagesParts[index] = MultipartBody.Part.createFormData("SurveyImage", file.getName(), surveyBody);
//        }


    }


}
