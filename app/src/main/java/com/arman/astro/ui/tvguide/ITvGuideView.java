package com.arman.astro.ui.tvguide;

import com.arman.astro.data.network.model.event.GetEventsResponse;
import com.arman.astro.data.network.model.simplechannel.GetSimpleChannelResponse;
import com.arman.astro.ui.base.IBaseView;

/**
 * Created by Arman on 5/17/2017.
 */

public interface ITvGuideView extends IBaseView {
    void updateData(GetEventsResponse response);
    void updateData(GetSimpleChannelResponse response);
}
