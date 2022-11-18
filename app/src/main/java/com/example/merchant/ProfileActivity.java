package com.example.merchant;

import android.content.Intent;
import android.os.Bundle;
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


    String uid, email, companyName, contactNo, cardNumber, expiryDate, ccv;
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

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference("Merchants");
                if (isChanged()) {
                    Toast.makeText(ProfileActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                    return;
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
                    cardNumber = merchant.cardNumber;
                    expiryDate = merchant.expiryDate;
                    ccv = merchant.ccv;
                    if (merchant != null) {
                        binding.companyNameTv.setText(merchant.companyName);
                        binding.contactNoTv.setText(merchant.contactNo);
                        binding.emailTv.setText(merchant.email);
                        binding.cardNumberTv.setText(merchant.cardNumber);
                        binding.expiryDateTv.setText(merchant.expiryDate);
                        binding.ccvTv.setText(merchant.ccv);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    private boolean isChanged() {
        if (!companyName.equals(binding.companyNameTv.getText().toString()) ||
                !contactNo.equals(binding.contactNoTv.getText().toString()) ||
                !cardNumber.equals(binding.cardNumberTv.getText().toString()) ||
                !expiryDate.equals(binding.expiryDateTv.getText().toString()) ||
                !ccv.equals(binding.ccvTv.getText().toString())) {
            reference.child(uid).child("companyName").setValue(binding.companyNameTv.getText().toString());
            reference.child(uid).child("contactNo").setValue(binding.contactNoTv.getText().toString());
            reference.child(uid).child("cardNumber").setValue(binding.cardNumberTv.getText().toString());
            reference.child(uid).child("expiryDate").setValue(binding.expiryDateTv.getText().toString());
            reference.child(uid).child("ccv").setValue(binding.ccvTv.getText().toString());
            return true;
        }

        return false;
    }
}