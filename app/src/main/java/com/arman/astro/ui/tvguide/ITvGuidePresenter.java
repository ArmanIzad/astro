package com.arman.astro.ui.tvguide;

import com.arman.astro.ui.base.IBasePresenter;

import java.util.List;
import java.util.Set;

/**
 * Created by Arman on 5/17/2017.
 */

public interface ITvGuidePresenter<T extends ITvGuideView> extends IBasePresenter<T> {
    void getSimplChannelList();
    void getEvents(String startTime, String endTime, List<String> channelIds);
    Set<String> getFavourites();
}
