package com.arman.astro.ui.base;

/**
 * Created by Arman on 5/15/2017.
 */

public interface IBaseView {
    void showLoading();

    void hideLoading();

    void onError(String message);
}
