package com.arman.astro.ui.channel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.arman.astro.R;
import com.arman.astro.data.network.model.channel.Channel;
import com.arman.astro.data.network.model.channel.GetChannelsResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class ChannelListActivity extends AppCompatActivity implements IChannelListView, IChannelListAdapterCallbacks {

    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_channels)
    RecyclerView rvChannels;

    private static int NUMBER_OF_COLUMNS = 2;

    private IChannelListPresenter presenter;
    private ChannelListAdapter adapter;
    private List<Channel> channels = new ArrayList<>();
    private Set<String> favourites = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        setSupportActionBar(toolbar);

        initPresenter();
        initViews();
    }

    private void initPresenter() {
//        presenter = new ChannelListPresenter();
//        presenter.attachView(this);
    }

    private void initViews() {
        adapter = new ChannelListAdapter(this, channels, favourites, this);
        rvChannels.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS, LinearLayoutManager.VERTICAL, false));
        rvChannels.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void onTvGuideClicked() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void updateData(GetChannelsResponse response) {
        channels.clear();
        channels.addAll(response.getChannel());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void onChannelSelected(long id) {

    }

    @Override
    public void onChannelFavourite(long id, boolean isFavourite) {

    }
}
