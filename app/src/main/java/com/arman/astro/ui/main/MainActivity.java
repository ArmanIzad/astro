package com.arman.astro.ui.main;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.arman.astro.AstroApplication;
import com.arman.astro.R;
import com.arman.astro.ui.channel.ChannelListFragment;
import com.arman.astro.ui.tvguide.TvGuideFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ChannelListFragment channelListFragment;
    private TvGuideFragment tvGuideFragment;

    private static String CHANNEL_LIST_TAG = "ChannelList";
    private static String TV_GUIDE_TAG = "TvGuide";
    private SortType currentSortType = SortType.ID;
    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Channel List",
                        "TV Guide"
                }));

        if (savedInstanceState == null) {
            channelListFragment = ChannelListFragment.newInstance();
            tvGuideFragment = TvGuideFragment.newInstance();
        }

        initializePresenter();
    }

    private void initializePresenter() {
        AstroApplication app = (AstroApplication) getApplication();
        presenter = new MainPresenter(app.getDataManager(), this);
    }

    @OnItemSelected(R.id.spinner)
    void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            showChannelList();
        } else {
            showTvGuide();
        }
    }

    private void showChannelList() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (channelListFragment.isAdded()) {
            ft.show(channelListFragment);
        } else {
            ft.add(R.id.container, channelListFragment, CHANNEL_LIST_TAG);
        }
        if (tvGuideFragment.isAdded()) {
            ft.hide(tvGuideFragment);
        }
        ft.commit();
    }

    private void showTvGuide() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (tvGuideFragment.isAdded()) {
            ft.show(tvGuideFragment);
        } else {
            ft.add(R.id.container, tvGuideFragment, TV_GUIDE_TAG);
        }
        if (channelListFragment.isAdded()) {
            ft.hide(channelListFragment);
        }
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort_channel_id:
                sortFragment(SortType.ID);
                return true;
            case R.id.action_sort_channel_name:
                sortFragment(SortType.NAME);
                return true;
            case R.id.action_sort_favourites:
                sortFragment(SortType.FAVOURITE);
                return true;
            case R.id.action_clear_favourites:
                presenter.clearFavourites();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortFragment(SortType sortType) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CHANNEL_LIST_TAG);
        if (!fragment.isVisible()) {
            fragment = getSupportFragmentManager().findFragmentByTag(TV_GUIDE_TAG);
        }

        IFragmentActions sort = (IFragmentActions) fragment;
        boolean isAsc = currentSortType != sortType;
        currentSortType = sortType;
        sort.sort(sortType, isAsc);
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
    public void refreshData() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CHANNEL_LIST_TAG);
        if (!fragment.isVisible()) {
            fragment = getSupportFragmentManager().findFragmentByTag(TV_GUIDE_TAG);
        }

        IFragmentActions refresh = (IFragmentActions) fragment;
        refresh.refreshFavourites();
    }

    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }
}
