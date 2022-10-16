package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.merchant.databinding.ActivityProjectDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProjectDetailsActivity extends AppCompatActivity {
    private ActivityProjectDetailsBinding binding;
    private ActionBar actionBar;

    public String projectId;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;
    private Query goalQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get project id passed from Project Fragment
        projectId = getIntent().getStringExtra("projectId");
        Log.d("projectId", projectId);

        // action bar title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Project Details");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = FirebaseDatabase.getInstance().getReference();
        query = reference.child("Project").child(projectId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Project project = snapshot.getValue(Project.class);
                if(project != null) {
                    goalQuery = reference.child("Goal").child(project.goal);
                    goalQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Goal goal = snapshot.getValue(Goal.class);
                            if (goal != null) {
                                Glide.with(getBaseContext()).load(goal.goalslogo).into((ImageView) findViewById(R.id.projectDetailsGoalIv));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    binding.projectDetailsTitleTv.setText(project.title);
                    binding.projectDetailsDescTv.setText(project.desc);
                    binding.projectDetailsUrlTv.setText(project.projecturl);
                    Glide.with(getBaseContext()).load(project.image).into((ImageView) findViewById(R.id.projectDetailsImgIv));
                }
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
}