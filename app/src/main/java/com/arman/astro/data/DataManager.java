package com.arman.astro.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.arman.astro.BuildConfig;
import com.arman.astro.data.network.IApiHelper;
import com.arman.astro.data.network.model.channel.GetChannelsRequest;
import com.arman.astro.data.network.model.channel.GetChannelsResponse;
import com.arman.astro.data.network.model.event.GetEventsRequest;
import com.arman.astro.data.network.model.event.GetEventsResponse;
import com.arman.astro.data.network.model.simplechannel.GetSimpleChannelResponse;
import com.arman.astro.data.preferences.IPreferenceHelper;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;


/**
 * Created by Arman on 5/16/2017.
 */

public class DataManager implements IPreferenceHelper, IApiHelper {

    private static final String PREF_KEY_FAVOURITE_LIST = "PREF_KEY_FAVOURITE_LIST";
    private static final String GET_CHANNELS_ENDPOINT = "ams/v3/getChannels";
    private static final String GET_SIMPLE_CHANNELS_ENDPOINT = "ams/v3/getChannelList";
    private static final String GET_EVENTS_ENDPOINT = "ams/v3/getEvents";

    private final SharedPreferences sharedPreferences;

    public DataManager(Context context, String prefName) {
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    @Override
    public void addToFavourite(String id) {
        Set<String> prefs = sharedPreferences.getStringSet(PREF_KEY_FAVOURITE_LIST, new HashSet<String>());
        prefs.add(id);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putStringSet(PREF_KEY_FAVOURITE_LIST, prefs).apply();
    }

    @Override
    public void removeFromFavourite(String id) {
        Set<String> prefs = sharedPreferences.getStringSet(PREF_KEY_FAVOURITE_LIST, new HashSet<String>());
        prefs.remove(id);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putStringSet(PREF_KEY_FAVOURITE_LIST, prefs).apply();
    }

    @Override
    public Set<String> getAllFavourites() {
        return sharedPreferences.getStringSet(PREF_KEY_FAVOURITE_LIST, new HashSet<String>());
    }

    @Override
    public void clearAllFavourites() {
        sharedPreferences.edit().putStringSet(PREF_KEY_FAVOURITE_LIST, new HashSet<String>()).apply();
    }

    @Override
    public Observable<GetChannelsResponse> astroGetChannels(GetChannelsRequest request) {
        return Rx2AndroidNetworking.get(BuildConfig.REQUEST_URL + GET_CHANNELS_ENDPOINT)
                .addQueryParameter(request)
                .build()
                .getObjectObservable(GetChannelsResponse.class);
    }

    @Override
    public Observable<GetSimpleChannelResponse> astroGetSimpleChannels() {
        return Rx2AndroidNetworking.get(BuildConfig.REQUEST_URL + GET_SIMPLE_CHANNELS_ENDPOINT)
                .build()
                .getObjectObservable(GetSimpleChannelResponse.class);
    }

    @Override
    public Observable<GetEventsResponse> astroGetEvents(GetEventsRequest request) {
//        String output = Rx2AndroidNetworking.get(BuildConfig.REQUEST_URL + GET_EVENTS_ENDPOINT)
//                .addQueryParameter(request)
//                .build().toString();
//        Log.d("request", request.toString());
//        Log.d("astroGetEvents", output);

        Map<String, String> map = new HashMap<>();

        map.put("periodStart", request.getPeriodStart());
        map.put("periodEnd", request.getPeriodEnd());
        map.put("channelId", request.getChannelIds().toString());


        return Rx2AndroidNetworking.get(BuildConfig.REQUEST_URL + GET_EVENTS_ENDPOINT)
                .addQueryParameter(map)
                .build()
                .getObjectObservable(GetEventsResponse.class);
    }
}
