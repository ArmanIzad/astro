
package com.arman.astro.data.network.model.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VernacularDatum {

    @SerializedName("vernacularLanguage")
    @Expose
    private String vernacularLanguage;
    @SerializedName("vernacularProgrammeTitle")
    @Expose
    private String vernacularProgrammeTitle;
    @SerializedName("vernacularShortSynopsis")
    @Expose
    private String vernacularShortSynopsis;
    @SerializedName("vernacularLongSynopsis")
    @Expose
    private String vernacularLongSynopsis;
    @SerializedName("actors")
    @Expose
    private String actors;
    @SerializedName("directors")
    @Expose
    private String directors;
    @SerializedName("producers")
    @Expose
    private String producers;

    public String getVernacularLanguage() {
        return vernacularLanguage;
    }

    public void setVernacularLanguage(String vernacularLanguage) {
        this.vernacularLanguage = vernacularLanguage;
    }

    public String getVernacularProgrammeTitle() {
        return vernacularProgrammeTitle;
    }

    public void setVernacularProgrammeTitle(String vernacularProgrammeTitle) {
        this.vernacularProgrammeTitle = vernacularProgrammeTitle;
    }

    public String getVernacularShortSynopsis() {
        return vernacularShortSynopsis;
    }

    public void setVernacularShortSynopsis(String vernacularShortSynopsis) {
        this.vernacularShortSynopsis = vernacularShortSynopsis;
    }

    public String getVernacularLongSynopsis() {
        return vernacularLongSynopsis;
    }

    public void setVernacularLongSynopsis(String vernacularLongSynopsis) {
        this.vernacularLongSynopsis = vernacularLongSynopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

}
