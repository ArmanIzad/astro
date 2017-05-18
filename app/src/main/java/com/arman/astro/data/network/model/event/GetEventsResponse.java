
package com.arman.astro.data.network.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetEventsResponse {

    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("getevent")
    @Expose
    private List<Getevent> getevent = null;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Getevent> getGetevent() {
        return getevent;
    }

    public void setGetevent(List<Getevent> getevent) {
        this.getevent = getevent;
    }

}
