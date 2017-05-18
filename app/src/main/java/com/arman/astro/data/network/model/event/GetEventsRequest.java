package com.arman.astro.data.network.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arman on 5/14/2017.
 */

public class GetEventsRequest {

    @SerializedName("periodStart")
    @Expose
    //YYYY-MM-DD HH:MM
    private String periodStart;
    @SerializedName("periodEnd")
    @Expose
    //YYYY-MM-DD HH:MM
    private String periodEnd;
    @SerializedName("channelId")
    @Expose
    private List<String> channelIds = null;
    @SerializedName("premier")
    @Expose
    private String premier;
    @SerializedName("live")
    @Expose
    private String live;
    @SerializedName("highlight")
    @Expose
    private String highlight;
    @SerializedName("channelCategory")
    @Expose
    private int channelCategory;
    @SerializedName("channelLanguage")
    @Expose
    private int channelLanguage;
    @SerializedName("siTrafficKey")
    @Expose
    private String siTrafficKey;
    @SerializedName("programmeId")
    @Expose
    private String programmeId;
    @SerializedName("episodeId")
    @Expose
    private String episodeId;

    public GetEventsRequest(List<String> channelIds, String periodStart, String periodEnd,
                            String premier, String live, String highlight, int channelCategory,
                            int channelLanguage, String siTrafficKey, String programmeId,
                            String episodeId) {
        this.channelIds = channelIds;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.premier = premier;
        this.live = live;
        this.highlight = highlight;
        this.channelCategory = channelCategory;
        this.channelLanguage = channelLanguage;
        this.siTrafficKey = siTrafficKey;
        this.programmeId = programmeId;
        this.episodeId = episodeId;
    }

    public GetEventsRequest(String periodStart, String periodEnd, List<String> channelIds) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.channelIds = channelIds;
//        this.premier = "true";
//        this.live = null;
//        this.highlight = null;
//        this.channelCategory = 0;
//        this.channelLanguage = 4;
//        this.siTrafficKey = null;
//        this.programmeId = null;
//        this.episodeId = null;
    }

    @Override
    public String toString() {
        return "GetEventsRequest{" +
                "channelIds=" + channelIds +
                ", periodStart='" + periodStart + '\'' +
                ", periodEnd='" + periodEnd + '\'' +
                ", premier='" + premier + '\'' +
                ", live='" + live + '\'' +
                ", highlight='" + highlight + '\'' +
                ", channelCategory=" + channelCategory +
                ", channelLanguage=" + channelLanguage +
                ", siTrafficKey='" + siTrafficKey + '\'' +
                ", programmeId='" + programmeId + '\'' +
                ", episodeId='" + episodeId + '\'' +
                '}';
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public List<String> getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(List<String> channelIds) {
        this.channelIds = channelIds;
    }

    public String getPremier() {
        return premier;
    }

    public void setPremier(String premier) {
        this.premier = premier;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public int getChannelCategory() {
        return channelCategory;
    }

    public void setChannelCategory(int channelCategory) {
        this.channelCategory = channelCategory;
    }

    public int getChannelLanguage() {
        return channelLanguage;
    }

    public void setChannelLanguage(int channelLanguage) {
        this.channelLanguage = channelLanguage;
    }

    public String getSiTrafficKey() {
        return siTrafficKey;
    }

    public void setSiTrafficKey(String siTrafficKey) {
        this.siTrafficKey = siTrafficKey;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }
}
