package com.arman.astro.ui.tvguide;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arman.astro.R;
import com.arman.astro.data.network.model.event.Getevent;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.arman.astro.util.DateTimeUtils.formatDate;

/**
 * Created by Arman on 5/15/2017.
 */

public class TvGuideAdapter extends RecyclerView.Adapter<TvGuideAdapter.EventViewHolder> {

    private List<Getevent> events;
    private Set<String> favourites;
    private Context context;

    public TvGuideAdapter(Context context, List<Getevent> events, Set<String> favourites) {
        this.context = context;
        this.events = events;
        this.favourites = favourites;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = parent.inflate(parent.getContext(), R.layout.adapter_guide, null);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Getevent event = events.get(position);
        if(favourites.contains(String.valueOf(event.getChannelId()))) {
            event.setFavourite(true);
        } else {
            event.setFavourite(false);
        }
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_channel_name)
        TextView tvChannelName;
        @BindView(R.id.tv_channel_id)
        TextView tvChannelId;
        @BindView(R.id.iv_favourite)
        ImageView ivFavourite;
        @BindView(R.id.tv_program_title)
        TextView tvProgramTitle;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Getevent event) {
            tvChannelName.setText(event.getChannelTitle());
            tvChannelId.setText(event.getChannelId() + "");
            tvProgramTitle.setText(event.getProgrammeTitle());
            String startTime = formatDate("yyyy-MM-dd HH:MM", "HH:MM", event.getDisplayDateTime());
            tvStartTime.setText(startTime);
            tvEndTime.setText(event.getDisplayDuration());
            if(event.isFavourite()) {
                ivFavourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favourite));
            } else {
                ivFavourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_not_favourite));
            }
        }
    }
}
