package com.example.merchant;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merchant.databinding.ActivityQuizBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class QuizActivity extends AppCompatActivity {
    public String customizedQuestId;
    String questId;
    private ActivityQuizBinding binding;
    private ActionBar actionBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;
    private RecyclerView quizList;
    private QuizAdapter quizAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Quizzes");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // get quest id passed from Quest Fragment
        questId = getIntent().getStringExtra("questId");
        Log.d("questId", questId);

        // Activity Recycler View
        quizList = (RecyclerView) findViewById(R.id.quizRv);
        quizList.setLayoutManager(new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference();

        // get quest ID
        reference.child("Quest").child(questId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    customizedQuestId = String.valueOf(dataSnapshot.child("questId").getValue());
                }
            }
        });

        query = reference.child("Quiz").orderByChild("questId").equalTo(customizedQuestId);
        FirebaseRecyclerOptions<Quiz> options =
                new FirebaseRecyclerOptions.Builder<Quiz>()
                        .setQuery(query, Quiz.class).build();

        quizAdapter = new QuizAdapter(options);
        quizList.setAdapter(quizAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        quizAdapter.startListening();

        // Remove crash on press back
        quizList.getRecycledViewPool().clear();
        quizAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        quizAdapter.stopListening();
    }
}