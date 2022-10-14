package com.example.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.merchant.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    // View Binding
    private ActivityProfileBinding binding;
    // action bar
    private ActionBar actionBar;
    // firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        checkUser();

        // logout user by clicking
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    private void checkUser() {
        // check if user is not logged then move to login activity

        // get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            // user not logged in, move to login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            // user logged in, get info
            String uid = user.getUid();
            String email = user.getEmail();
            // retrieve merchant's info by merchant's UID
            reference = firebaseDatabase.getReference("Merchants").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Merchant merchant = snapshot.getValue(Merchant.class);
                    if (merchant != null) {
                        binding.companyNameTv.setText(merchant.companyName);
                        binding.contactNoTv.setText(merchant.contactNo);
                        binding.emailTv.setText(merchant.email);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}