package com.arman.astro.ui.channel;

import com.arman.astro.data.network.model.channel.GetChannelsResponse;
import com.arman.astro.ui.base.IBaseView;

/**
 * Created by Arman on 5/15/2017.
 */

public interface IChannelListView extends IBaseView {
    void updateData(GetChannelsResponse response);
    void refresh();
}
