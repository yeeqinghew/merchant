package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.merchant.databinding.ActivityProjectDetailsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
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

public class ProjectDetailsActivity extends AppCompatActivity {
    private ActivityProjectDetailsBinding binding;
    private ActionBar actionBar;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    public String projectId;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private Query query;
    private Query goalQuery;
    private Uri uri;

    String uid;
    Boolean hasCreditCard = false;

    String cardNumber, expiryDate, ccv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get project id passed from Project Fragment
        projectId = getIntent().getStringExtra("projectId");

        // action bar title
        actionBar = getSupportActionBar();
        actionBar.setTitle("Project Details");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        binding.projectDetailsUrlTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        reference = FirebaseDatabase.getInstance().getReference();
        query = reference.child("Project").child(projectId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Project project = snapshot.getValue(Project.class);
                if(project != null) {
                    goalQuery = reference.child("Goal").orderByChild("goalstitle").equalTo(project.getGoaltitle());
                    goalQuery.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Goal goal = snapshot.getValue(Goal.class);
                            if (goal != null) {
                                Glide.with(getBaseContext()).load(goal.goalslogo).into((ImageView) findViewById(R.id.projectDetailsGoalIv));
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
                    binding.projectDetailsTitleTv.setText(project.title);
                    binding.projectDetailsDescTv.setText(project.desc);
                    binding.projectDetailsUrlTv.setText(project.url);
                    uri = Uri.parse(project.url);
                    Glide.with(getBaseContext()).load(project.file).into((ImageView) findViewById(R.id.projectDetailsImgIv));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
                if (hasCreditCard) {
                    Toast.makeText(ProjectDetailsActivity.this, "You've donated with your credit card!", Toast.LENGTH_SHORT).show();
                } else {
                    dialogBuilder = new AlertDialog.Builder(ProjectDetailsActivity.this);
                    final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
                    dialogBuilder.setView(popupView);
                    dialog = dialogBuilder.create();
                    dialog.show();

                    Button donateBtn = (Button) dialog.findViewById(R.id.donateBtn);
                    Button cancelBtn = (Button) dialog.findViewById(R.id.cancelBtn);

                    donateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cardNumber = ((EditText) dialog.findViewById(R.id.cardNumberTv)).getText().toString();
                            expiryDate = ((EditText) dialog.findViewById(R.id.expiryDateTv)).getText().toString();
                            ccv = ((EditText) dialog.findViewById(R.id.ccvTv)).getText().toString();

                            reference = FirebaseDatabase.getInstance().getReference("Merchants");
                            if (!TextUtils.isEmpty(cardNumber) & !TextUtils.isEmpty(expiryDate) & !TextUtils.isEmpty(ccv) ){
                                reference.child(uid).child("cardNumber").setValue(cardNumber);
                                reference.child(uid).child("expiryDate").setValue(expiryDate);
                                reference.child(uid).child("ccv").setValue(ccv);
                                dialog.dismiss();
                                Toast.makeText(ProjectDetailsActivity.this, "Updated your credit card!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProjectDetailsActivity.this, "Please input all fields for your credit card.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    private void checkUser() {
        // get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            // user not logged in, move to login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            // user logged in, get info
            uid = user.getUid();
            // retrieve merchant's info by merchant's UID
            reference = firebaseDatabase.getReference("Merchants").child(uid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Merchant merchant = snapshot.getValue(Merchant.class);
                    if (merchant != null) {
                        if (!merchant.cardNumber.equals("0")) {
                            hasCreditCard = true;

                            return;
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}