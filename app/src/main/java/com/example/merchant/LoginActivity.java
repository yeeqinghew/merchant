package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.merchant.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // View Binding
    private ActivityLoginBinding binding;
    // action bar
    private ActionBar actionBar;
    private String email = "", password = "";
    // progress dialog
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        // configure progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in");
        progressDialog.setCanceledOnTouchOutside(false);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        // if have account click to goto signup
        binding.haveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate data
                validateData();
            }
        });
    }

    private void checkUser() {
        // check if user is already logged in
        // if already logged in then open profile page

        // get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
    }

    private void validateData() {
        // get data
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();

        // validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // email format is invalid, don't proceed further
            binding.emailEt.setError("Invalid email format");
        } else if (TextUtils.isEmpty(password)) {
            // no password is entered
            binding.passwordEt.setError("Enter password.");
        } else {
            // data is valid, now continue Firebase
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        // show progress
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // login success
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String email = user.getEmail();
                Toast.makeText(LoginActivity.this, "Logged In\n" + email, Toast.LENGTH_SHORT).show();

                // open profile activity
                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // login failure
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}