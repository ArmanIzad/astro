package com.arman.astro.ui.channel;

import android.util.Log;

import com.arman.astro.data.DataManager;
import com.arman.astro.data.network.model.channel.GetChannelsResponse;

import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arman on 5/15/2017.
 */

public class ChannelListPresenter<T extends IChannelListView> implements IChannelListPresenter<T> {

    private IChannelListView view;
    private DataManager dataManager;

    public ChannelListPresenter(DataManager dataManager, IChannelListView view) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void getChannels() {
        view.showLoading();
        dataManager.astroGetChannels(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetChannelsResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull GetChannelsResponse getChannelsResponse) throws Exception {
                        view.hideLoading();
                        //getChannelsResponse.addFavourites(dataManager.getAllFavourites());
                        view.updateData(getChannelsResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        view.hideLoading();
                        view.onError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void addRemoveChannelToFavourite(String channelId, boolean isAdded) {
        Log.d("addRemoveChannelToFavourite", channelId + isAdded);
        if(isAdded) {
            dataManager.addToFavourite(channelId);
        } else {
            dataManager.removeFromFavourite(channelId);
        }
        view.refresh();
    }

    @Override
    public Set<String> getFavourites() {
        return dataManager.getAllFavourites();
    }
}
