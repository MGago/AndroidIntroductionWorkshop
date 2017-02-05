package com.example.mariogago.introductionworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mariogago.introductionworkshop.api.SearchResponse;

import java.util.List;

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
        private final TextView nameText;

        private EventViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.event_item_name);
        }

        private void bind(SearchResponse.Event event) {
            nameText.setText(event.name.text);
        }
    }
}
