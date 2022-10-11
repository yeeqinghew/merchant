package com.example.merchant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.merchant.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {


    // View Binding
    private ActivityProfileBinding binding;
    // action bar
    private ActionBar actionBar;
    // firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");


        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
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
            String email = user.getEmail();
            binding.emailTv.setText(email);
        }
    }
}