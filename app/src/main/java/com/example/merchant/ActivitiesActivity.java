package com.example.merchant;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.merchant.databinding.ActivityActivitiesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ActivitiesActivity extends AppCompatActivity {

    private ActivityActivitiesBinding binding;
    private ActionBar actionBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;

    private RecyclerView activitiesList;
    ActivityAdapter activityAdapter;

    String questId;
    Activity currentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActivitiesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Activities");

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // get quest id passed from Quest Fragment
        questId = getIntent().getStringExtra("questId");
        Log.d("questId", questId);

        // Activity Recycler View
        activitiesList = (RecyclerView) findViewById(R.id.activitiesRv);
        activitiesList.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference();
        query = reference.child("Activities").orderByChild("questId").equalTo(questId);

        FirebaseRecyclerOptions<Activity> options =
                new FirebaseRecyclerOptions.Builder<Activity>()
                        .setQuery(query, Activity.class).build();

        activityAdapter = new ActivityAdapter(options);
        activitiesList.setAdapter(activityAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        activityAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityAdapter.stopListening();
    }
}