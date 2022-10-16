package com.example.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

    String uid, email, companyName, contactNo;

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

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: find merchant and update it
                reference = FirebaseDatabase.getInstance().getReference("Merchants");
                if (isNameChanged() || isContactNoChanged()) {
                    Toast.makeText(ProfileActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(ProfileActivity.this, "No changes has been made", Toast.LENGTH_LONG).show();
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
            uid = user.getUid();
            email = user.getEmail();
            // retrieve merchant's info by merchant's UID
            reference = firebaseDatabase.getReference("Merchants").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Merchant merchant = snapshot.getValue(Merchant.class);
                    companyName = merchant.companyName;
                    contactNo = merchant.contactNo;
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


    private boolean isNameChanged() {
        if (!companyName.equals(binding.companyNameTv.getText().toString())) {
            Log.d("companyName", binding.companyNameTv.getText().toString());
            reference.child(uid).child("companyName").setValue(binding.companyNameTv.getText().toString());
            return true;
        }

        return false;
    }

    private boolean isContactNoChanged() {
        if (!contactNo.equals(binding.contactNoTv.getText().toString())) {
            Log.d("contactNo", binding.contactNoTv.getText().toString());
            reference.child(uid).child("contactNo").setValue(binding.contactNoTv.getText().toString());
            return true;
        }

        return false;
    }
}