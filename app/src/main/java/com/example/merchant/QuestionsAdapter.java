package com.example.merchant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {
    Context context;
    List<HashMap<String, Object>> questions;

    public QuestionsAdapter(Context context, List<HashMap<String, Object>> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);

        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(view);
        return questionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        HashMap<String, Object> questionMap = questions.get(position);
        Log.d("questionMap", String.valueOf(questionMap.get("questionTitle")));
        String questionTitle = questionMap.get("questionTitle").toString();

        holder.questionTitleTv.setText(questionTitle);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
