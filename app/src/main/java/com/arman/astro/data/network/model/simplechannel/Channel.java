
package com.arman.astro.data.network.model.simplechannel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("channelId")
    @Expose
    private Integer channelId;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("channelStbNumber")
    @Expose
    private Integer channelStbNumber;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Integer getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(Integer channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

}
