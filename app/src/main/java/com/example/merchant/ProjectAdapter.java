package com.example.merchant;

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

public class ProjectAdapter extends FirebaseRecyclerAdapter<Project, ProjectAdapter.ProjectViewHolder> {

    public ProjectAdapter(@NonNull FirebaseRecyclerOptions<Project> options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ProjectViewHolder holder, int position, @NonNull Project project) {
        Glide.with(holder.projectImage.getContext()).load(project.getImage()).into(holder.projectImage);
        holder.projectTitle.setText(project.getTitle());
        holder.projectDescription.setText(project.getDesc());
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(v);
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        ImageView projectImage;
        TextView projectTitle, projectDescription;
        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            projectImage = itemView.findViewById(R.id.projectImageTV);
            projectTitle = itemView.findViewById(R.id.projectTitleTv);
            projectDescription = itemView.findViewById(R.id.projectDescriptionTv);
        }
    }
}
