package com.arman.astro.ui.main;

import com.arman.astro.ui.base.IBasePresenter;

/**
 * Created by Arman on 5/19/2017.
 */

public interface IMainPresenter<T extends IMainView> extends IBasePresenter<T> {
    void clearFavourites();
}
