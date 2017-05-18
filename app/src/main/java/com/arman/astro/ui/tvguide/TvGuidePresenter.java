package com.arman.astro.ui.tvguide;

import com.arman.astro.data.DataManager;
import com.arman.astro.data.network.model.event.GetEventsRequest;
import com.arman.astro.data.network.model.event.GetEventsResponse;
import com.arman.astro.data.network.model.simplechannel.GetSimpleChannelResponse;

import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Arman on 5/17/2017.
 */

public class TvGuidePresenter implements ITvGuidePresenter<ITvGuideView> {

    ITvGuideView view;
    DataManager dataManager;

    public TvGuidePresenter(DataManager dataManager, ITvGuideView view) {
        this.dataManager = dataManager;
        this.view = view;
    }

    @Override
    public void getSimplChannelList() {
        view.showLoading();
        dataManager.astroGetSimpleChannels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetSimpleChannelResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull GetSimpleChannelResponse response) throws Exception {
                        view.hideLoading();
                        view.updateData(response);
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
    public void getEvents(String startTime, String endTime, List<String> channelIds) {
        view.showLoading();
        dataManager.astroGetEvents(new GetEventsRequest(startTime, endTime, channelIds))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetEventsResponse>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull GetEventsResponse getEventsResponse) throws Exception {
                        view.hideLoading();
                        view.updateData(getEventsResponse);
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
    public Set<String> getFavourites() {
        return dataManager.getAllFavourites();
    }
}
