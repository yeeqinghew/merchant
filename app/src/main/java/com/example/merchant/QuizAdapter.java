package com.example.merchant;

import android.util.Log;
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
        holder.quizPointsTv.setText(model.points);
        holder.quizTypeChip.setText(model.quizType);
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quizPointsTv;
        Chip quizTypeChip;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            quizPointsTv = itemView.findViewById(R.id.quizPointsTv);
            quizTypeChip = itemView.findViewById(R.id.quizTypeChip);
        }
    }
}
