package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.merchant.databinding.ActivityRewardDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RewardDetailActivity extends AppCompatActivity {

    private ActivityRewardDetailBinding binding;
    private ActionBar actionBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;

    String rewardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRewardDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        rewardId = getIntent().getStringExtra("rewardId");
        query = reference.child("Rewards").child(rewardId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Reward reward = snapshot.getValue(Reward.class);
                if (reward != null) {
                    Glide.with(getBaseContext()).load(reward.mImageUrl).into((ImageView) findViewById(R.id.rewardImageTv));
                    Glide.with(getBaseContext()).load(reward.qrUrl).into((ImageView) findViewById(R.id.rewardQRCodeTv));
                    actionBar.setTitle(reward.title);
                    binding.rewardDescriptionTv.setText(reward.description);
                    binding.rewardPointsTv.setText(reward.point);
                    binding.rewardExpiryDateTv.setText(reward.expirydate);
                    binding.rewardTouTv.setText(reward.tou);
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