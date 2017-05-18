package com.arman.astro.ui.tvguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arman.astro.AstroApplication;
import com.arman.astro.R;
import com.arman.astro.data.network.model.channel.ChannelCompator;
import com.arman.astro.data.network.model.event.GetEventsResponse;
import com.arman.astro.data.network.model.event.Getevent;
import com.arman.astro.data.network.model.simplechannel.Channel;
import com.arman.astro.data.network.model.simplechannel.GetSimpleChannelResponse;
import com.arman.astro.ui.main.EndlessRecyclerViewScrollListener;
import com.arman.astro.ui.main.IFragmentActions;
import com.arman.astro.ui.main.SortType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.arman.astro.util.DateTimeUtils.formatDate;

/**
 * Created by Arman on 5/17/2017.
 */

public class TvGuideFragment extends Fragment implements ITvGuideView, IFragmentActions {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.rv_channels)
    RecyclerView recyclerView;

    private ITvGuidePresenter presenter;
    private TvGuideAdapter adapter;
    private List<Channel> simpleChannel = new ArrayList<>();
    private List<Getevent> events = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    private Set<String> favourites = new HashSet<>();

    private static final int NUMBER_REQUEST = 10;
    private int page = 0;

    public TvGuideFragment() {
    }

    public static TvGuideFragment newInstance() {
        return new TvGuideFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_channel_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initPresenter();
        initViews();
        presenter.getSimplChannelList();
    }

    private void getEvents() {
        if(simpleChannel.size() <= page * NUMBER_REQUEST) {
            return;
        }

        List<String> channelIds = new ArrayList<>();
        for(int i = 1; i <= NUMBER_REQUEST; ++i) {
            channelIds.add(i +  (page * NUMBER_REQUEST) + "");
        }

        //YYYY-MM-DD HH:MM
        Date date = Calendar.getInstance().getTime();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.HOUR,1);
        Date endDate = endCalendar.getTime();
        String fromDate = formatDate(date, "yyyy-MM-dd HH:MM");
        String toDate = formatDate(endDate, "yyyy-MM-dd HH:MM");
        Log.d("fromDate", fromDate);
        Log.d("toDate", toDate);
        Log.d("channelIds", channelIds.toString());
        presenter.getEvents(fromDate, toDate, channelIds);
        page++;
    }

    private void initPresenter() {
        AstroApplication app = (AstroApplication) getActivity().getApplication();
        presenter = new TvGuidePresenter(app.getDataManager(), this);
    }

    private void initViews() {
        adapter = new TvGuideAdapter(getContext(), events, favourites);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getEvents();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void showLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if(progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(String message) {
        final Snackbar snack = Snackbar.make(progressBar, message,
                Snackbar.LENGTH_INDEFINITE);
        snack.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snack.dismiss();
            }
        });
        snack.show();
    }

    @Override
    public void updateData(GetEventsResponse response) {
        events.addAll(response.getGetevent());
        favourites.clear();
        favourites.addAll(presenter.getFavourites());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(GetSimpleChannelResponse response) {
        simpleChannel = response.getChannels();
        getEvents();
    }

    @Override
    public void sort(SortType sortType, boolean isAsc) {
        switch (sortType) {
            case ID: {
                Collections.sort(events, new ChannelCompator(ChannelCompator.CompareField.ID));
                break;
            }
            case FAVOURITE: {
                Collections.sort(events, new ChannelCompator(ChannelCompator.CompareField.FAVOURITE));
                break;
            }
            default: {
                Collections.sort(events, new ChannelCompator(ChannelCompator.CompareField.NAME));
                break;
            }
        }

        if(!isAsc) {
            Collections.reverse(events);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshFavourites() {
        favourites.clear();
        favourites.addAll(presenter.getFavourites());
        adapter.notifyDataSetChanged();
    }
}
