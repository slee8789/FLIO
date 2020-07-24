package com.fund.flio.ui.base;

public interface BaseNavigator {
    void showToast(String message);

    void handleError(Throwable throwable);
}
