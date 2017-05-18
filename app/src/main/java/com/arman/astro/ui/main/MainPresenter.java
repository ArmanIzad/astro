package com.arman.astro.ui.main;

import com.arman.astro.data.DataManager;

/**
 * Created by Arman on 5/19/2017.
 */

public class MainPresenter implements IMainPresenter<IMainView> {

    IMainView view;
    DataManager dataManager;

    public MainPresenter(DataManager dataManager, IMainView view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    @Override
    public void clearFavourites() {
        dataManager.clearAllFavourites();
        view.refreshData();
    }
}
