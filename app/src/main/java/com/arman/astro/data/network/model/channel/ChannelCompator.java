package com.arman.astro.data.network.model.channel;

import com.arman.astro.data.network.model.IResponse;

import java.util.Comparator;

/**
 * Created by Arman on 5/15/2017.
 */

public class ChannelCompator implements Comparator<IResponse> {

    public enum CompareField {
        ID,
        NAME,
        FAVOURITE
    }

    private CompareField compareField = CompareField.ID;

    public ChannelCompator(CompareField compareField) {
        this.compareField = compareField;
    }

    @Override
    public int compare(IResponse o1, IResponse o2) {
        switch (compareField) {
            case NAME:
                return o1.getChannelTitle().compareTo(o2.getChannelTitle());
            case FAVOURITE:
                return Boolean.compare(o1.isFavourite(), o2.isFavourite()) * -1;
            default:
                return o1.getChannelId().compareTo(o2.getChannelId());
        }
    }
}
