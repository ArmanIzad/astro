package com.arman.astro.ui.channel;

import com.arman.astro.ui.base.IBasePresenter;

import java.util.Set;

/**
 * Created by Arman on 5/15/2017.
 */

public interface IChannelListPresenter<T extends IChannelListView> extends IBasePresenter<T> {
    void getChannels();
    void addRemoveChannelToFavourite(String channelId, boolean isAdded);
    Set<String> getFavourites();
}
