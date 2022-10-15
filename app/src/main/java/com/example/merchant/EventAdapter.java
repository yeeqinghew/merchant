package com.example.merchant;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventAdapter extends FirebaseRecyclerAdapter<CreateEvent, EventAdapter.EventViewHolder> {

    public EventAdapter(@NonNull FirebaseRecyclerOptions<CreateEvent> options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull CreateEvent event) {
        // display image, title and description for event
        Glide.with(holder.eventImage.getContext()).load(event.getImage()).into(holder.eventImage);
        holder.eventTitle.setText(event.getEventtitle());
        holder.eventDescription.setText(event.getEventdescription());
        holder.eventDate.setText(event.getEventdate());
        holder.eventLocation.setText(event.getEventlocation());

        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("eventId", getRef(position).getKey());
                Log.d("EVENT ADAPTER", getRef(position).getKey());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(v);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView eventTitle, eventDescription, eventDate, eventLocation;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.eventImageTv);
            eventTitle = itemView.findViewById(R.id.eventTitleTv);
            eventDescription = itemView.findViewById(R.id.eventDescriptionTv);
            eventDate = itemView.findViewById(R.id.eventDateTv);
            eventLocation = itemView.findViewById(R.id.eventLocationTv);
        }
    }
}
