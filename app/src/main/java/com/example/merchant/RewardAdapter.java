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

public class RewardAdapter extends FirebaseRecyclerAdapter<Reward, RewardAdapter.RewardViewHolder> {

    public RewardAdapter(@NonNull FirebaseRecyclerOptions<Reward> options) {
        super(options);
    }


    @Override
    public void onBindViewHolder(@NonNull RewardAdapter.RewardViewHolder holder, int position, @NonNull Reward reward) {
            Glide.with(holder.rewardImage.getContext()).load(reward.getmImageUrl()).into(holder.rewardImage);
            holder.rewardTitle.setText(reward.getTitle());
            holder.rewardPoints.setText(reward.getPoint());
    }

    @NonNull
    @Override
    public RewardAdapter.RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_item, parent, false);
        return new RewardViewHolder(v);
    }

    public static class RewardViewHolder extends RecyclerView.ViewHolder {
        ImageView rewardImage;
        TextView rewardTitle, rewardPoints;
        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);

            rewardImage = itemView.findViewById(R.id.rewardImageTv);
            rewardTitle = itemView.findViewById(R.id.rewardTitleTv);
            rewardPoints = itemView.findViewById(R.id.rewardPointsTv);
        }
    }
}
