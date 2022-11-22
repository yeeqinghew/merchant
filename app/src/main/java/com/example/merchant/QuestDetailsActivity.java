package com.example.merchant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.merchant.databinding.ActivityQuestDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestDetailsActivity extends AppCompatActivity {
    private ActivityQuestDetailsBinding binding;
    private ActionBar actionBar;
    private ProgressDialog progressDialog;

    public String questId;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private DatabaseReference reference;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get quest id passed from Quest Fragment
        questId = getIntent().getStringExtra("questId");
        Log.d("customizedQuestId", questId);

        // action bar title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Quest Details");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // get current user
        user = firebaseAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference();
        query = reference.child("Quest").orderByChild("questId").equalTo(questId);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Quest quest = snapshot.getValue(Quest.class);
                Log.d("questtttt", String.valueOf(quest));
                if (quest != null) {
                    binding.questDetailsTitle.setText(quest.questTitle);
                    binding.questDetailsDesc.setText(quest.description);
                    binding.questPointsChip.setText(quest.points);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

        binding.clickToViewLinkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityActivity.class);
                intent.putExtra("questId", questId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        binding.clickToViewQuizTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                intent.putExtra("questId", questId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Success");
        progressDialog.setMessage("You have joined the quest");
        progressDialog.setCanceledOnTouchOutside(false);

        reference = FirebaseDatabase.getInstance().getReference("JoinedQuests");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> joinedQuests = snapshot.getChildren();

                for(DataSnapshot joinedQuest: joinedQuests) {
                    JoinedQuest q = joinedQuest.getValue(JoinedQuest.class);

                    if(q != null) {
                        if(q.merchantId.equalsIgnoreCase(user.getUid().toString()) && q.questId.equalsIgnoreCase(questId)) {
                            binding.joinBtn.setText("You have joined the quest");
                            binding.joinBtn.setEnabled(false);
                            binding.clickToViewLinkTv.setVisibility(View.VISIBLE);
                            binding.clickToViewQuizTv.setVisibility(View.VISIBLE);
                            return;
                        }
                    }
                }

                binding.joinBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseDatabase = FirebaseDatabase.getInstance();

                        reference = FirebaseDatabase.getInstance().getReference("JoinedQuests");

                        JoinedQuest joinedQuest = new JoinedQuest();
                        joinedQuest.setQuestId(questId);
                        joinedQuest.setMerchantId(user.getUid());
                        reference.push().setValue(joinedQuest).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
                                finish();
                            }
                        });
                    }
                });
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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}