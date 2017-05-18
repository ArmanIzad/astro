package com.arman.astro.ui.channel;

/**
 * Created by Arman on 5/15/2017.
 */

public interface IChannelListAdapterCallbacks {
    void onChannelSelected(long id);
    void onChannelFavourite(long id, boolean isFavourite);
}
