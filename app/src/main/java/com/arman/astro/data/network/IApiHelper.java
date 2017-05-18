package com.arman.astro.data.network;

import com.arman.astro.data.network.model.channel.GetChannelsRequest;
import com.arman.astro.data.network.model.channel.GetChannelsResponse;
import com.arman.astro.data.network.model.event.GetEventsRequest;
import com.arman.astro.data.network.model.event.GetEventsResponse;
import com.arman.astro.data.network.model.simplechannel.GetSimpleChannelResponse;

import io.reactivex.Observable;

/**
 * Created by Arman on 5/14/2017.
 */

public interface IApiHelper {

    Observable<GetChannelsResponse> astroGetChannels(GetChannelsRequest request);
    Observable<GetSimpleChannelResponse> astroGetSimpleChannels();
    Observable<GetEventsResponse> astroGetEvents(GetEventsRequest request);
}
