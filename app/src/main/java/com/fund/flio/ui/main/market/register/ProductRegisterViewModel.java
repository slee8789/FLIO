package com.fund.flio.ui.main.market.register;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.BoxYn;
import com.fund.flio.data.enums.FlioYn;
import com.fund.flio.data.enums.PurchaseKind;
import com.fund.flio.data.enums.Purpose;
import com.fund.flio.data.enums.RepairYn;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.enums.TradeKind;
import com.fund.flio.data.enums.UseDate;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedimagepicker.builder.TedRxImagePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.fund.flio.utils.CommonUtils.getRealPathFromUri;

public class ProductRegisterViewModel extends BaseViewModel {

    private Context mContext;

    public MutableLiveData<List<Uri>> mThumbnailUris = new MutableLiveData<>();

    public MutableLiveData<List<Uri>> getThumbnailUris() {
        return mThumbnailUris;
    }

    public ObservableField<UseDate> useDate = new ObservableField<>(UseDate.A);
    public ObservableField<SaleYn> saleYn = new ObservableField<>(SaleYn.R);
    public ObservableField<BoxYn> boxYn = new ObservableField<>(BoxYn.Y);
    public ObservableField<PurchaseKind> purchaseKind = new ObservableField<>(PurchaseKind.Y);
    public ObservableField<TradeKind> tradeKind = new ObservableField<>(TradeKind.DIRECT);
    public ObservableField<RepairYn> repairYn = new ObservableField<>(RepairYn.Y);
    public ObservableField<FlioYn> flioYn = new ObservableField<>(FlioYn.Y);
    public ObservableField<String> productRelatedUrl = new ObservableField<>();
    public ObservableField<String> model = new ObservableField<>();
    public ObservableField<String> serial = new ObservableField<>();
    public ObservableField<String> brand = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> productPrice = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();

    public MutableLiveData<String> tag = new MutableLiveData<>();
    public ObservableField<String> tagCount = new ObservableField<>();
    public ObservableField<String> imageCount = new ObservableField<>(String.valueOf(0));

    public ObservableBoolean detailState = new ObservableBoolean(false);
    public MutableLiveData<String> inputTags = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Purpose>> purposes = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<ArrayList<Purpose>> getPurposes() {
        return purposes;
    }

    public MutableLiveData<String> getInputTags() {
        return inputTags;
    }

    public TextWatcher watcherInputTags = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Logger.d("beforeTextChanged " + s);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Logger.d("onTextChanged " + s);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Logger.d("afterTextChanged " + editable);
            if (editable.toString().contains(" ")) {
                inputTags.setValue(editable.toString().trim() + "#");
            }
        }
    };

    public ProductRegisterViewModel(Context context, DataManager dataManager, SchedulerProvider schedulerProvider, ResourceProvider resourceProvider) {
        super(dataManager, schedulerProvider, resourceProvider);
        Logger.d("ProductRegisterViewModel constructor");
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

    public ObservableBoolean wayDelivery = new ObservableBoolean(true);

    public void tradeKindSelect(View v) {
        switch (v.getId()) {
            case R.id.way_direct:
                wayDelivery.set(true);
                tradeKind.set(TradeKind.DIRECT);
                break;
            case R.id.way_delivery:
                wayDelivery.set(false);
                tradeKind.set(TradeKind.DELIVERY);
                break;
        }
    }

    private RequestBody paramImgBody, paramTitle, paramContent, paramSaleYn, paramTag, paramUseDate, paramPurchaseKind, paramProductPrice, paramTradeKind, paramBoxYn, paramBrand, paramPurpose, paramModelNo, paramSerialNo, paramRepairYn, paramProductRelatedUrl, paramUid;
    private MultipartBody.Part[] imgUrls;

    public void registerProduct(View view) {

        if (mThumbnailUris.getValue() != null) {
            imgUrls = new MultipartBody.Part[mThumbnailUris.getValue().size()];
            for (int i = 0; i < mThumbnailUris.getValue().size(); i++) {
                File file = new File(getRealPathFromUri(mContext, mThumbnailUris.getValue().get(i)));
                paramImgBody = RequestBody.create(MediaType.parse("image/*"), file);
                imgUrls[i] = MultipartBody.Part.createFormData("imgList", file.getName(), paramImgBody);
            }
        }


        paramTitle = RequestBody.create(MediaType.parse("text/plain"), title.get());
        paramContent = RequestBody.create(MediaType.parse("text/plain"), content.get());
        paramTradeKind = RequestBody.create(MediaType.parse("text/plain"), tradeKind.get().name());
//        RequestBody status = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        paramSaleYn = RequestBody.create(MediaType.parse("text/plain"), saleYn.get().name());
        paramTag = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");

//        RequestBody displayYn = RequestBody.create(MediaType.parse("text/plain"), "Y");
        if (detailState.get()) {
            paramUseDate = RequestBody.create(MediaType.parse("text/plain"), useDate.get().name());
            paramBoxYn = RequestBody.create(MediaType.parse("text/plain"), boxYn.get().name());
            paramPurchaseKind = RequestBody.create(MediaType.parse("text/plain"), purchaseKind.get().name());
            paramRepairYn = RequestBody.create(MediaType.parse("text/plain"), repairYn.get().name());
            if (brand.get() != null) {
                paramBrand = RequestBody.create(MediaType.parse("text/plain"), brand.get());
            }
            if (model.get() != null) {
                paramModelNo = RequestBody.create(MediaType.parse("text/plain"), model.get());
            }
            if (productRelatedUrl.get() != null) {
                paramProductRelatedUrl = RequestBody.create(MediaType.parse("text/plain"), productRelatedUrl.get());
            }
            if (serial.get() != null) {
                paramSerialNo = RequestBody.create(MediaType.parse("text/plain"), serial.get());
            }
        }


//        RequestBody purchasePrice = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");
        if (productPrice.get() != null) {
            paramProductPrice = RequestBody.create(MediaType.parse("text/plain"), productPrice.get());
        }


        paramPurpose = RequestBody.create(MediaType.parse("text/plain"), TextUtils.join(",", purposes.getValue()));


        paramUid = RequestBody.create(MediaType.parse("text/plain"), getDataManager().getUserId());

        //Todo : loading progress
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().insertProduct(paramTitle, paramContent,paramSaleYn, paramTag, imgUrls, paramUseDate, paramPurchaseKind, paramProductPrice, paramTradeKind, paramBoxYn, paramBrand, paramPurpose, paramModelNo, paramSerialNo, paramRepairYn, paramProductRelatedUrl, paramUid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    Logger.i("multipart success");
                    setIsLoading(false);
                    if (Void.isSuccessful()) {
                        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigateUp();
                    }
                }, onError -> {
                    Logger.e("multipart error " + onError.getMessage());
                    setIsLoading(false);
                }));

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

    public void goDetail(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductRegisterFragmentDirections.actionNavMarketProductRegisterToNavMarketProductRegisterDetail());
    }

    public void showPurpose(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductRegisterFragmentDirections.actionNavMarketProductRegisterToNavMarketProductRegisterPurpose());
    }

    public void showCategory(View view) {
//        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductRegisterFragmentDirections.actionNavMarketProductRegisterToNavMarketProductRegisterDetail());
    }

    public void showTagWrite(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductRegisterFragmentDirections.actionNavMarketProductRegisterToNavMarketProductRegisterTag());
    }

    public void onUseDateClick(UseDate useDate) {
        this.useDate.set(useDate);
    }

    public void onBoxYnClick(BoxYn boxYn) {
        this.boxYn.set(boxYn);
    }

    public void onPurchaseKindClick(PurchaseKind purchaseKind) {
        this.purchaseKind.set(purchaseKind);
    }

    public void onRepairYnClick(RepairYn repairYn) {
        this.repairYn.set(repairYn);
    }

    public void onFlioYnClick(FlioYn repairYn) {
        this.flioYn.set(repairYn);
    }

    public void onPurposeClick(View v, Purpose purpose) {

        if (purposes.getValue().size() == 3) {
            if (((CheckBox) v).isChecked()) {
                ((CheckBox) v).setChecked(false);
                Snackbar snackBar = Snackbar.make(((MainActivity) v.getContext()).getViewDataBinding().getRoot(), "3개까지 선택 가능합니다.", Snackbar.LENGTH_SHORT);
                final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBar.getView().getLayoutParams();
                snackBar.getView().setLayoutParams(params);
                snackBar.setBackgroundTint(getResourceProvider().getColor(R.color.purple));
                snackBar.show();
            } else {
                purposes.getValue().remove(purpose);
                purposes.setValue(purposes.getValue());
            }
        } else {
            Logger.d("purpose else " + ((CheckBox) v).isChecked());
            if (((CheckBox) v).isChecked()) {
                purposes.getValue().add(purpose);
            } else {
                purposes.getValue().remove(purpose);
            }
            purposes.setValue(purposes.getValue());
        }
        Logger.d("purpose size " + purposes.getValue().size());
    }

    public void onPurposeDelete(Purpose purpose) {
        purposes.getValue().remove(purpose);
        purposes.setValue(purposes.getValue());
    }

    public void setDetailState(View v) {
        detailState.set(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.d("onCleared");
    }
}
