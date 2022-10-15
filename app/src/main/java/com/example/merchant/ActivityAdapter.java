package com.example.merchant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class ActivityAdapter extends FirebaseRecyclerAdapter<Activity, ActivityAdapter.ActivityViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ActivityAdapter(@NonNull FirebaseRecyclerOptions<Activity> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ActivityViewHolder holder, int position, @NonNull Activity model) {
        holder.activityTitleTv.setText(model.getActivityTitle());
        holder.activityGoalTitleTv.setText(model.getGoalTitle());
        holder.activityPointsTv.setText(model.getPoints());
        holder.activityDescriptionTv.setText(model.getActivityDescription());
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        return new ActivityViewHolder(view);
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView activityTitleTv;
        TextView activityGoalTitleTv;
        TextView activityPointsTv;
        TextView activityDescriptionTv;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            activityTitleTv = itemView.findViewById(R.id.activityTitleTv);
            activityGoalTitleTv = itemView.findViewById(R.id.activityGoalTitleTv);
            activityPointsTv = itemView.findViewById(R.id.activityPointsTv);
            activityDescriptionTv = itemView.findViewById(R.id.activityDescriptionTv);
        }

    }
}
