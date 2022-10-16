package com.example.merchant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.chip.Chip;

public class QuizAdapter extends FirebaseRecyclerAdapter<Quiz, QuizAdapter.QuizViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuizAdapter(@NonNull FirebaseRecyclerOptions<Quiz> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuizViewHolder holder, int position, @NonNull Quiz model) {
        holder.quizDescTv.setText(model.getDescription());
        holder.quizPointsTv.setText(model.getPoints());
        holder.quizTypeChip.setText(model.getQuizType());
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quizDescTv;
        TextView quizPointsTv;
        Chip quizTypeChip;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            quizDescTv = itemView.findViewById(R.id.quizDescTv);
            quizPointsTv = itemView.findViewById(R.id.quizPointsTv);
            quizTypeChip = itemView.findViewById(R.id.quizTypeChip);
        }
    }
}
