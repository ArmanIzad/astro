package com.arman.astro.data.network.model.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arman on 5/15/2017.
 */

public class GetChannelsRequest {

    @SerializedName("channelId")
    @Expose
    private List<String> channelIds = null;
    @SerializedName("language")
    @Expose
    private List<String> language = null;
    @SerializedName("category")
    @Expose
    private List<String> category = null;
    @SerializedName("platform")
    @Expose
    private List<String> platform = null;

}
