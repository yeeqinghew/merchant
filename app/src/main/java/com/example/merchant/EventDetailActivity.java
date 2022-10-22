package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.merchant.databinding.ActivityActivitiesBinding;
import com.example.merchant.databinding.ActivityEventDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventDetailActivity extends AppCompatActivity {

    private ActivityEventDetailBinding binding;
    private ActionBar actionBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;
    private Query goalQuery;

    String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        actionBar.setTitle("Event Detail");
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        eventId = getIntent().getStringExtra("eventId");
        query = reference.child("CreateEvent").child(eventId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CreateEvent event = snapshot.getValue(CreateEvent.class);
                if (event != null) {
                    // get image from Goal table
                    goalQuery = reference.child("Goal").orderByChild("goalstitle").equalTo(event.getGoaltitle());
                    goalQuery.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Goal goal = snapshot.getValue(Goal.class);
                            Log.d("GOAL LOGO", String.valueOf(goal));
                            if (goal != null) {
                                Glide.with(getBaseContext()).load(goal.getGoalslogo()).into((ImageView) findViewById(R.id.goalTitleTv));
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

                    binding.eventTitleTv.setText(event.getEventtitle());
                    binding.eventDateTv.setText(event.getEventdate());
                    binding.eventPointTv.setText(event.getEventpoints());
                    binding.eventStartTimeTv.setText(event.getEventstarttime());
                    binding.untilTv.setText("-");
                    binding.eventEndTimeTv.setText(event.getEventendtime());
                    binding.eventDescriptionTv.setText(event.getEventdescription());
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