package com.example.merchant;

import android.content.Context;
import android.content.Intent;
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

import org.w3c.dom.Text;

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
        holder.quizTitle.setText(model.quizID);

        String quizId = model.quizID;

        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionsActivity.class);
                intent.putExtra("quizId", quizId);
                context.startActivity(intent);
            }
        });
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
        TextView quizTitle;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            quizPointsTv = itemView.findViewById(R.id.quizPointsTv);
            quizTypeChip = itemView.findViewById(R.id.quizTypeChip);
            quizTitle = itemView.findViewById(R.id.quizIdTv);
        }
    }
}
