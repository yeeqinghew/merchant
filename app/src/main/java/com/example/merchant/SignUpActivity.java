package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.merchant.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    // ViewBinding
    private ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private String companyName = "", contactNo = "", email = "", password = "";
    // progress dialog
    private ProgressDialog progressDialog;
    // action bar
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // configure action bar, title, back button
        actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // init firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // configure progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Creating your account...");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    private void validateData() {
        companyName = binding.companyNameEt.getText().toString();
        contactNo = binding.contactNoEt.getText().toString().trim();
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();

        // validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // email format is invalid, don't proceed further
            binding.emailEt.setError("Invalid email format.");
        } else if (TextUtils.isEmpty(companyName)) {
            binding.companyNameEt.setError("Enter company name.");
        } else if (TextUtils.isEmpty(contactNo)) {
            binding.contactNoEt.setError("Enter contact number.");
        } else if (TextUtils.isEmpty(email)) {
            binding.companyNameEt.setError("Enter email.");
        } else if (TextUtils.isEmpty(password)) {
            // no password is entered
            binding.passwordEt.setError("Enter password.");
        } else if (contactNo.length() < 8 || contactNo.length() > 8) {
            // contactNo length less or more than 8
            binding.contactNoEt.setError("Contact number must be at least 8 digits.");
        } else if (password.length() < 6) {
            // password length less than 6
            binding.passwordEt.setError("Password must be at least 6 characters long.");
        } else {
            // data is valid, now continue Firebase
            firebaseSignUp();
        }
    }

    private void firebaseSignUp() {
        // show progress
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // sign up success
                progressDialog.dismiss();
                // get user info
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String email = user.getEmail();

                // create merchant account in Realtime Database so we can store info about merchant and retrieve them later
                Merchant merchant = new Merchant(companyName, contactNo, email);
                firebaseDatabase.getReference("Merchants")
                        .child(user.getUid())
                        .setValue(merchant).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(SignUpActivity.this, "Account created\n" + email, Toast.LENGTH_SHORT).show();
                                // open homepage activity
                                startActivity(new Intent(SignUpActivity.this, HomepageActivity.class));
                                finish();
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // sign up failed
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}