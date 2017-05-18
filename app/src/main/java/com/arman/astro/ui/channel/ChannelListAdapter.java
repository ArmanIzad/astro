package com.arman.astro.ui.channel;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arman.astro.R;
import com.arman.astro.data.network.model.channel.Channel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arman on 5/15/2017.
 */

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder> {

    List<Channel> channels;
    Set<String> favourites;
    IChannelListAdapterCallbacks callback;
    Context context;

    public ChannelListAdapter(Context context, List<Channel> channels, Set<String> favourites, IChannelListAdapterCallbacks callback) {
        this.context = context;
        this.channels = channels;
        this.callback = callback;
        this.favourites = favourites;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = parent.inflate(parent.getContext(), R.layout.adapter_channel, null);
        ChannelViewHolder viewHolder = new ChannelViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        Channel channel = channels.get(position);
        if(favourites.contains(String.valueOf(channel.getChannelId()))) {
            channel.setFavourite(true);
        } else {
            channel.setFavourite(false);
        }
        holder.bind(channel);
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel_name)
        TextView tvChannelName;
        @BindView(R.id.tv_channel_id)
        TextView tvChannelId;
        @BindView(R.id.iv_favourite)
        ImageView ivFavourite;
        @BindView(R.id.cl_root)
        View clRoot;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Channel channel) {
            tvChannelName.setText(channel.getChannelTitle());
            tvChannelId.setText(channel.getChannelId() + "");
            Picasso.with(context).load(channel.getImageUrl()).into(ivChannel);
            if(channel.isFavourite()) {
                ivFavourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favourite));
            } else {
                ivFavourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_not_favourite));
            }
            ivFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onChannelFavourite(channel.getChannelId(), !channel.isFavourite());
                }
            });

//            ivChannel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    callback.onChannelSelected(channel.getChannelId());
//                }
//            });
        }
    }
}
