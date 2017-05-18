package com.arman.astro.ui.channel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arman.astro.AstroApplication;
import com.arman.astro.R;
import com.arman.astro.data.network.model.channel.Channel;
import com.arman.astro.data.network.model.channel.ChannelCompator;
import com.arman.astro.data.network.model.channel.GetChannelsResponse;
import com.arman.astro.ui.main.IFragmentActions;
import com.arman.astro.ui.main.SortType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arman on 5/17/2017.
 */

public class ChannelListFragment extends Fragment implements IChannelListView, IChannelListAdapterCallbacks, IFragmentActions {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.rv_channels)
    RecyclerView recyclerView;

    private static int NUMBER_OF_COLUMNS = 2;

    private IChannelListPresenter presenter;
    private ChannelListAdapter adapter;
    private List<Channel> channels = new ArrayList<>();
    private Set<String> favourites = new HashSet<>();

    public ChannelListFragment() {
    }

    public static ChannelListFragment newInstance() {
        return new ChannelListFragment();
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
        presenter.getChannels();
    }

    private void initPresenter() {
        AstroApplication app = (AstroApplication) getActivity().getApplication();
        presenter = new ChannelListPresenter(app.getDataManager(), this);
    }

    private void initViews() {
        adapter = new ChannelListAdapter(getContext(), channels, favourites, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUMBER_OF_COLUMNS, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
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
    public void updateData(GetChannelsResponse response) {
        channels.clear();
        channels.addAll(response.getChannel());
        favourites.clear();
        favourites.addAll(presenter.getFavourites());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        favourites.clear();
        favourites.addAll(presenter.getFavourites());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChannelSelected(long id) {

    }

    @Override
    public void onChannelFavourite(long id, boolean isFavourite) {
        presenter.addRemoveChannelToFavourite(id + "", isFavourite);
    }

    @Override
    public void sort(SortType sortType, boolean isAsc) {
        switch (sortType) {
            case ID: {
                Collections.sort(channels, new ChannelCompator(ChannelCompator.CompareField.ID));
                break;
            }
            case FAVOURITE: {
                Collections.sort(channels, new ChannelCompator(ChannelCompator.CompareField.FAVOURITE));
                break;
            }
            default: {
                Collections.sort(channels, new ChannelCompator(ChannelCompator.CompareField.NAME));
                break;
            }
        }

        if(!isAsc) {
            Collections.reverse(channels);
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
