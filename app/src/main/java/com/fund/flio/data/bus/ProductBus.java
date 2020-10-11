package com.fund.flio.data.bus;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ProductBus {

    private static ProductBus productBus;
    private final PublishSubject<Boolean> dismissSubject;

    private ProductBus() {
        dismissSubject = PublishSubject.create();
    }

    public static ProductBus getInstance() {
        if (productBus == null) {
            productBus = new ProductBus();
        }
        return productBus;
    }

    public void reviewWriteDismiss() {
        dismissSubject.onNext(true);
    }

    public Observable<Boolean> getReviewWriteDismiss() {
        return dismissSubject.ofType(Boolean.class);
    }

}
