package com.example.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.merchant.databinding.ActivityQuestDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestDetailsActivity extends AppCompatActivity {
    private ActivityQuestDetailsBinding binding;
//    private ActionBar actionBar;

    public String questId;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get quest id passed from Quest Fragment
        questId = getIntent().getStringExtra("questId");
        Log.d("questId", questId);

        // action bar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quest Details");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        binding.clickToViewLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityActivity.class);
                intent.putExtra("questId", questId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        binding.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get current user
                user = firebaseAuth.getCurrentUser();
                Log.d("Current User", user.getDisplayName());

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();

                reference = FirebaseDatabase.getInstance().getReference("JoinedQuests");

                JoinedQuest joinedQuest = new JoinedQuest();
                joinedQuest.setQuestId(questId);
                joinedQuest.setMerchantId(user.getUid());
                reference.push().setValue(joinedQuest);
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Quest");
        reference.child(questId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful() && task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    String questTitle = String.valueOf(dataSnapshot.child("questTitle").getValue());
                    String questDesc = String.valueOf(dataSnapshot.child("description").getValue());
                    String questPoints = String.valueOf(dataSnapshot.child("points").getValue());
                    String questWhen = String.valueOf(dataSnapshot.child("when").getValue());
                    String questWho = String.valueOf(dataSnapshot.child("who").getValue());

                    binding.questDetailsTitle.setText(questTitle);
                    binding.questDetailsWhen.setText(questWhen);
                    binding.questDetailsWho.setText(questWho);
                    binding.questDetailsDesc.setText(questDesc);
                    binding.questPointsChip.setText(questPoints);

                }
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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}