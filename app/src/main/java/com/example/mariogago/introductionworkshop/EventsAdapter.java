package com.example.mariogago.introductionworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mariogago.introductionworkshop.api.SearchResponse;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private final List<SearchResponse.Event> events;

    public EventsAdapter(List<SearchResponse.Event> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_name,
                parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        SearchResponse.Event event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameText;
        private final TextView locationText;
        private final TextView dateText;

        private EventViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.event_item_name);
            locationText = (TextView) itemView.findViewById(R.id.event_item_location);
            dateText = (TextView) itemView.findViewById(R.id.event_item_date);
            imageView = (ImageView) itemView.findViewById(R.id.event_item_image);
        }

        private void bind(SearchResponse.Event event) {
            nameText.setText(event.name.text);
            locationText.setText(event.venue.name);

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat
                    .MEDIUM, Locale.getDefault());
            dateText.setText(dateFormat.format(event.start.local));

            if (event.logo == null || event.logo.url == null) {
                imageView.setImageResource(0);
            } else {
                Picasso.with(itemView.getContext()).load(event.logo.url).centerCrop().fit().into
                        (imageView);
            }

        }
    }
}
