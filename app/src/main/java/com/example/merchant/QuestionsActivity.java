package com.example.merchant;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merchant.databinding.ActivityQuestionsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsActivity extends AppCompatActivity {

    private ActivityQuestionsBinding binding;
    private ActionBar actionBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;

    String quizId;
    private RecyclerView questionsList;
//    QuestionAdapter questionAdapter;

    private List<HashMap<String, Object>> questions;
    private QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Questions");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // get quiz id passed from Quiz Activity
        quizId = getIntent().getStringExtra("quizId");
        Log.d("quizId", quizId);

        // Activity Recycler View
//        questionsList = (RecyclerView) findViewById(R.id.questionsRv);
//        questionsList.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<Question> options =
//                new FirebaseRecyclerOptions.Builder<Question>()
//                        .setQuery(query, Question.class).build();
//
//        questionAdapter = new QuestionAdapter(options);
//        questionsList.setAdapter(questionAdapter);

        reference = FirebaseDatabase.getInstance().getReference();
        query = reference.child("Questions").orderByChild("quizId").equalTo(quizId);
        questions = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        questionsList = (RecyclerView) findViewById(R.id.questionsRv);
        questionsList.setLayoutManager(linearLayoutManager);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                questions = (List<HashMap<String, Object>>) snapshot.child("questions").getValue();
                Log.d("CALUE", String.valueOf(questions));

                questionsAdapter = new QuestionsAdapter(QuestionsActivity.this, questions);
                questionsList.setAdapter(questionsAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                questionsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Remove crash on press back
//        questionsList.getRecycledViewPool().clear();
//        questionsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}