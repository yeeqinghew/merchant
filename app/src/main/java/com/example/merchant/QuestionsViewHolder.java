package com.example.merchant;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionsViewHolder extends RecyclerView.ViewHolder {
    TextView questionTitleTv;
    TextView questionAnswerTv;

    public QuestionsViewHolder(@NonNull View itemView) {
        super(itemView);
        questionTitleTv = itemView.findViewById(R.id.questionTitleTv);
        questionAnswerTv = itemView.findViewById(R.id.questionAnswerTv);
    }
}
