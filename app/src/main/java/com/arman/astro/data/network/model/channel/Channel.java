
package com.arman.astro.data.network.model.channel;

import com.arman.astro.data.network.model.IResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Channel implements IResponse {

    @SerializedName("channelId")
    @Expose
    private Long channelId;
    @SerializedName("siChannelId")
    @Expose
    private String siChannelId;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("channelDescription")
    @Expose
    private String channelDescription;
    @SerializedName("channelLanguage")
    @Expose
    private String channelLanguage;
    @SerializedName("channelColor1")
    @Expose
    private Object channelColor1;
    @SerializedName("channelColor2")
    @Expose
    private Object channelColor2;
    @SerializedName("channelColor3")
    @Expose
    private Object channelColor3;
    @SerializedName("channelCategory")
    @Expose
    private String channelCategory;
    @SerializedName("channelStbNumber")
    @Expose
    private String channelStbNumber;
    @SerializedName("channelHD")
    @Expose
    private Boolean channelHD;
    @SerializedName("hdSimulcastChannel")
    @Expose
    private Long hdSimulcastChannel;
    @SerializedName("channelStartDate")
    @Expose
    private Object channelStartDate;
    @SerializedName("channelEndDate")
    @Expose
    private Object channelEndDate;
    @SerializedName("channelExtRef")
    @Expose
    private List<ChannelExtRef> channelExtRef = null;
    @SerializedName("linearOttMapping")
    @Expose
    private List<LinearOttMapping> linearOttMapping = null;

    private boolean isFavourite;

    @Override
    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getSiChannelId() {
        return siChannelId;
    }

    public void setSiChannelId(String siChannelId) {
        this.siChannelId = siChannelId;
    }

    @Override
    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getChannelLanguage() {
        return channelLanguage;
    }

    public void setChannelLanguage(String channelLanguage) {
        this.channelLanguage = channelLanguage;
    }

    public Object getChannelColor1() {
        return channelColor1;
    }

    public void setChannelColor1(Object channelColor1) {
        this.channelColor1 = channelColor1;
    }

    public Object getChannelColor2() {
        return channelColor2;
    }

    public void setChannelColor2(Object channelColor2) {
        this.channelColor2 = channelColor2;
    }

    public Object getChannelColor3() {
        return channelColor3;
    }

    public void setChannelColor3(Object channelColor3) {
        this.channelColor3 = channelColor3;
    }

    public String getChannelCategory() {
        return channelCategory;
    }

    public void setChannelCategory(String channelCategory) {
        this.channelCategory = channelCategory;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public Boolean getChannelHD() {
        return channelHD;
    }

    public void setChannelHD(Boolean channelHD) {
        this.channelHD = channelHD;
    }

    public Long getHdSimulcastChannel() {
        return hdSimulcastChannel;
    }

    public void setHdSimulcastChannel(Long hdSimulcastChannel) {
        this.hdSimulcastChannel = hdSimulcastChannel;
    }

    public Object getChannelStartDate() {
        return channelStartDate;
    }

    public void setChannelStartDate(Object channelStartDate) {
        this.channelStartDate = channelStartDate;
    }

    public Object getChannelEndDate() {
        return channelEndDate;
    }

    public void setChannelEndDate(Object channelEndDate) {
        this.channelEndDate = channelEndDate;
    }

    public List<ChannelExtRef> getChannelExtRef() {
        return channelExtRef;
    }

    public void setChannelExtRef(List<ChannelExtRef> channelExtRef) {
        this.channelExtRef = channelExtRef;
    }

    public List<LinearOttMapping> getLinearOttMapping() {
        return linearOttMapping;
    }

    public void setLinearOttMapping(List<LinearOttMapping> linearOttMapping) {
        this.linearOttMapping = linearOttMapping;
    }

    @Override
    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getImageUrl() {
        String url = "";
        if(!channelExtRef.isEmpty()) {
            url = channelExtRef.get(0).getValue();
        }

        return url;
    }
}
