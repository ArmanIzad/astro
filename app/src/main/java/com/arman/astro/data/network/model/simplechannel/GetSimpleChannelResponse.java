
package com.arman.astro.data.network.model.simplechannel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSimpleChannelResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

}
