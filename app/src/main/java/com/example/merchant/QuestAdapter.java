package com.example.merchant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class QuestAdapter extends FirebaseRecyclerAdapter<Quest, QuestAdapter.QuestViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuestAdapter(@NonNull FirebaseRecyclerOptions<Quest> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestViewHolder holder, int position, @NonNull Quest model) {
        holder.questTitleTv.setText(model.getQuestTitle());
        holder.questGoalTitleTv.setText(model.getGoalTitle());
        holder.questDescriptionTv.setText(model.getDescription());
        holder.questPoints.setText(model.getPoints());

        String customizedQuestId = model.questId;

        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestDetailsActivity.class);
                intent.putExtra("questId", customizedQuestId);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_item, parent, false);
        return new QuestViewHolder(view);
    }

    public class QuestViewHolder extends RecyclerView.ViewHolder {
        TextView questTitleTv;
        TextView questGoalTitleTv;
        TextView questDescriptionTv;
        TextView questPoints;

        public QuestViewHolder(@NonNull View itemView) {
            super(itemView);

            questTitleTv = itemView.findViewById(R.id.questTitleTv);
            questGoalTitleTv = itemView.findViewById(R.id.questGoalTitleTv);
            questDescriptionTv = itemView.findViewById(R.id.questDescriptionTv);
            questPoints = itemView.findViewById(R.id.questPoints);
        }
    }
}
