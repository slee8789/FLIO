package com.fund.flio.ui.main.market.register;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.fund.flio.R;
import com.fund.flio.data.DataManager;
import com.fund.flio.data.enums.AccessoryType;
import com.fund.flio.data.enums.AcousticType;
import com.fund.flio.data.enums.AmpType;
import com.fund.flio.data.enums.BoxYn;
import com.fund.flio.data.enums.CableType;
import com.fund.flio.data.enums.DiyType;
import com.fund.flio.data.enums.FlioYn;
import com.fund.flio.data.enums.HeadSetType;
import com.fund.flio.data.enums.MikeType;
import com.fund.flio.data.enums.ProductCategory;
import com.fund.flio.data.enums.PurchaseKind;
import com.fund.flio.data.enums.Purpose;
import com.fund.flio.data.enums.RecordType;
import com.fund.flio.data.enums.RepairYn;
import com.fund.flio.data.enums.SaleYn;
import com.fund.flio.data.enums.SourceType;
import com.fund.flio.data.enums.SpeakerType;
import com.fund.flio.data.enums.TradeKind;
import com.fund.flio.data.enums.UseDate;
import com.fund.flio.di.provider.ResourceProvider;
import com.fund.flio.di.provider.SchedulerProvider;
import com.fund.flio.ui.base.BaseViewModel;
import com.fund.flio.ui.main.MainActivity;
import com.fund.flio.utils.CommonUtils;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.snackbar.Snackbar;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedimagepicker.builder.TedRxImagePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.fund.flio.data.enums.UseDate.A;
import static com.fund.flio.utils.CommonUtils.getRealPathFromUri;

public class ProductRegisterViewModel extends BaseViewModel {

    private Context mContext;

    public ObservableField<UseDate> useDate = new ObservableField<>(A);
    public ObservableField<SaleYn> saleYn = new ObservableField<>(SaleYn.R);
    public ObservableField<BoxYn> boxYn = new ObservableField<>(BoxYn.Y);
    public ObservableField<PurchaseKind> purchaseKind = new ObservableField<>(PurchaseKind.Y);
    public ObservableField<TradeKind> tradeKind = new ObservableField<>(TradeKind.DIRECT);
    public ObservableField<RepairYn> repairYn = new ObservableField<>(RepairYn.N);
    public ObservableField<FlioYn> flioYn = new ObservableField<>(FlioYn.N);
    public ObservableField<String> productRelatedUrl = new ObservableField<>();
    public ObservableField<String> model = new ObservableField<>();
    public ObservableField<String> serial = new ObservableField<>();
    public ObservableField<String> brand = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> categoryDepth1 = new ObservableField<>(ProductCategory.SPEAKER.name());
    public MutableLiveData<String> tag = new MutableLiveData<>();
    public ObservableField<String> tagCount = new ObservableField<>();
    public ObservableField<String> imageCount = new ObservableField<>(String.valueOf(0));
    public ObservableField<Boolean> detailState = new ObservableField(false);
    public MutableLiveData<String> productPrice = new MutableLiveData<>();
    public MutableLiveData<String> inputTags = new MutableLiveData<>();
    public ObservableField<String> categoryDepth2 = new ObservableField<>();
    private MutableLiveData<String> categoryDepth2String = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Purpose>> purposes = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<Uri>> mThumbnailUris = new MutableLiveData<>();

    private RequestBody paramImgBody, paramTitle, paramContent, paramCategoryDepth1, paramCategoryDepth2, paramSaleYn, paramTag, paramUseDate, paramPurchaseKind, paramProductPrice, paramTradeKind, paramBoxYn, paramFlioYn, paramBrand, paramPurpose, paramModelNo, paramSerialNo, paramRepairYn, paramProductRelatedUrl, paramUid;
    private MultipartBody.Part[] imgUrls;

    public MutableLiveData<List<Uri>> getThumbnailUris() {
        return mThumbnailUris;
    }

    public MutableLiveData<ArrayList<Purpose>> getPurposes() {
        return purposes;
    }

    public MutableLiveData<String> getInputTags() {
        return inputTags;
    }

    public MutableLiveData<String> getProductPrice() {
        return productPrice;
    }

    public MutableLiveData<String> getCategoryDepth2String() {
        return categoryDepth2String;
    }

    public void setInitial() {
        useDate.set(A);
        saleYn.set(SaleYn.R);
        boxYn.set(BoxYn.Y);
        purchaseKind.set(PurchaseKind.Y);
        tradeKind.set(TradeKind.DIRECT);
        repairYn.set(RepairYn.Y);
        flioYn.set(FlioYn.Y);
        productRelatedUrl.set("");
        model.set("");
        serial.set("");
        brand.set("");
        title.set("");
        productPrice.setValue("");
        content.set("");
        categoryDepth1.set("");
        categoryDepth2.set("");
        tag.setValue("");
        imageCount.set("0");
        if (mThumbnailUris.getValue() != null) {
            mThumbnailUris.getValue().clear();
        }
        detailState.set(false);
        inputTags.setValue("");
        if (purposes.getValue() != null) {
            purposes.getValue().clear();
        }

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
        getCompositeDisposable2().add(CommonUtils.permissionCheck(v.getContext())
                .subscribe(permissionResult -> {
                    Logger.d("permissionResult " + permissionResult.isGranted());
                    if (permissionResult.isGranted()) {
                        getCompositeDisposable2().add(TedRxImagePicker
                                .with(v.getContext())
                                .max(10, getResourceProvider().getString(R.string.message_max_count))
                                .showCameraTile(true)
                                .startMultiImage()
                                .subscribe(uriList -> {
                                    imageCount.set(String.valueOf(uriList.size()));
                                    mThumbnailUris.setValue(uriList);
                                }));
                    } else {

                    }
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
        paramCategoryDepth1 = RequestBody.create(MediaType.parse("text/plain"), categoryDepth1.get());
        paramCategoryDepth2 = RequestBody.create(MediaType.parse("text/plain"), categoryDepth2.get());
        paramTradeKind = RequestBody.create(MediaType.parse("text/plain"), tradeKind.get().name());
        paramSaleYn = RequestBody.create(MediaType.parse("text/plain"), saleYn.get().name());
//        paramTag = RequestBody.create(MediaType.parse("text/plain"), "COMMUNITY");

        if (detailState.get()) {
            paramUseDate = RequestBody.create(MediaType.parse("text/plain"), useDate.get().name());
            paramBoxYn = RequestBody.create(MediaType.parse("text/plain"), boxYn.get().name());
            paramFlioYn = RequestBody.create(MediaType.parse("text/plain"), flioYn.get().name());
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

        if (productPrice.getValue() != null) {
            paramProductPrice = RequestBody.create(MediaType.parse("text/plain"), productPrice.getValue().replaceAll(",", ""));
        }


        paramPurpose = RequestBody.create(MediaType.parse("text/plain"), TextUtils.join(",", purposes.getValue()));


        paramUid = RequestBody.create(MediaType.parse("text/plain"), getDataManager().getUserId());

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().insertProduct(paramTitle,
                paramContent,
                paramCategoryDepth1,
                paramCategoryDepth2,
                paramSaleYn,
                null,
                imgUrls,
                paramUseDate,
                paramPurchaseKind,
                paramProductPrice,
                paramTradeKind,
                paramBoxYn,
                paramFlioYn,
                paramBrand,
                paramPurpose,
                paramModelNo,
                paramSerialNo,
                paramRepairYn,
                paramProductRelatedUrl,
                paramUid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(Void -> {
                    Logger.i("multipart success");
                    setIsLoading(false);
                    if (Void.isSuccessful()) {
                        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigateUp();
                        setInitial();
                    }
                }, onError -> {
                    Logger.e("multipart error " + onError.getMessage());
                    setIsLoading(false);
                }));

    }

    public void setCategoryOrPurpose(View view) {
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigateUp();
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
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigate(ProductRegisterFragmentDirections.actionNavMarketProductRegisterToNavMarketProductRegisterCategory());
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

    public void setDetailState(View view) {
        detailState.set(true);
        Navigation.findNavController((Activity) view.getContext(), R.id.fragment_container).navigateUp();
    }

    public void onCategoryClick(View v, int position, String category) {
        Logger.i("onCategoryClick " + position + ", " + category + ", " + categoryDepth1.get() + ", " + categoryDepth2.get() + ", " + categoryDepth2String.getValue());
        switch (ProductCategory.valueOf(categoryDepth1.get())) {
            case SPEAKER:
                this.categoryDepth2.set(SpeakerType.values()[position + 1].name());
                break;
            case MIKE:
                this.categoryDepth2.set(MikeType.values()[position + 1].name());
                break;
            case CABLE:
                this.categoryDepth2.set(CableType.values()[position + 1].name());
                break;
            case AMP:
                this.categoryDepth2.set(AmpType.values()[position + 1].name());
                break;
            case SOURCE:
                this.categoryDepth2.set(SourceType.values()[position + 1].name());
                break;
            case HEADSET:
                this.categoryDepth2.set(HeadSetType.values()[position + 1].name());
                break;
            case ACOUSTIC:
                this.categoryDepth2.set(AcousticType.values()[position + 1].name());
                break;
            case RECORD:
                this.categoryDepth2.set(RecordType.values()[position + 1].name());
                break;
            case ACCESSORY:
                this.categoryDepth2.set(AccessoryType.values()[position + 1].name());
                break;
            case DIY:
                this.categoryDepth2.set(DiyType.values()[position + 1].name());
                break;
        }
        categoryDepth2String.setValue(category);
    }

    public void setCategory(int position) {
        switch (position) {
            case 0:
                this.categoryDepth1.set(ProductCategory.SPEAKER.name());
                break;
            case 1:
                this.categoryDepth1.set(ProductCategory.MIKE.name());
                break;
            case 2:
                this.categoryDepth1.set(ProductCategory.CABLE.name());
                break;
            case 3:
                this.categoryDepth1.set(ProductCategory.AMP.name());
                break;
            case 4:
                this.categoryDepth1.set(ProductCategory.SOURCE.name());
                break;
            case 5:
                this.categoryDepth1.set(ProductCategory.HEADSET.name());
                break;
            case 6:
                this.categoryDepth1.set(ProductCategory.ACOUSTIC.name());
                break;
            case 7:
                this.categoryDepth1.set(ProductCategory.RECORD.name());
                break;
            case 8:
                this.categoryDepth1.set(ProductCategory.ACCESSORY.name());
                break;
            case 9:
                this.categoryDepth1.set(ProductCategory.DIY.name());
                break;
        }
    }

    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private String result = "";

    public TextWatcher watcherPrice = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Logger.d("beforeTextChanged " + s);

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Logger.d("onTextChanged " + s);
            if (!TextUtils.isEmpty(s.toString()) && !s.toString().equals(result)) {
                result = decimalFormat.format(Double.parseDouble(s.toString().replaceAll(",", "")));
                productPrice.setValue(result);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Logger.d("afterTextChanged " + editable);


        }
    };

    @Override
    protected void onCleared() {
        super.onCleared();
        Logger.d("onCleared");
    }
}
