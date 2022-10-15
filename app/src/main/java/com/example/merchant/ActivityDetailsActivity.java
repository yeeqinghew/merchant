package com.example.merchant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.merchant.databinding.ActivityDetailsBinding;
import com.example.merchant.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ActivityDetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    private ImageView qrDataIv;
    private TextView activityDetailsTitle;
    private TextView activityDetailsGoalTitle;
    private TextView activityDetailsDesc;

    private ActionBar actionBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    public String activityId;
    public String qrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // get quest id passed from Quest Fragment
        activityId = getIntent().getStringExtra("activityId");
        Log.d("activityId", activityId);

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("Activities");
        reference.child(activityId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String activityDesc = String.valueOf(dataSnapshot.child("activityDescription").getValue());
                        String activityTitle = String.valueOf(dataSnapshot.child("activityTitle").getValue());
                        String goalTitle = String.valueOf(dataSnapshot.child("goalTitle").getValue());
                        qrData = String.valueOf(dataSnapshot.child("qrdata").getValue());

                        // action bar title
                        actionBar = getSupportActionBar();
                        actionBar.setTitle(activityTitle);
                        actionBar.setDisplayHomeAsUpEnabled(true);

                        binding.activityDetailsDesc.setText(activityDesc);
                        binding.activityDetailsTitle.setText(activityTitle);
                        binding.activityDetailsGoalTitle.setText(goalTitle);

                        //initializing MultiFormatWriter for QR code
                        MultiFormatWriter mWriter = new MultiFormatWriter();
                        try {
                            //BitMatrix class to encode entered text and set Width & Height
                            BitMatrix mMatrix = mWriter.encode(qrData, BarcodeFormat.QR_CODE, 400,400);
                            BarcodeEncoder mEncoder = new BarcodeEncoder();
                            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
                            binding.qrDataIv.setImageBitmap(mBitmap);//Setting generated QR code to imageView
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }


                    } else {
                        Toast.makeText(ActivityDetailsActivity.this, "Activity does not exist.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityDetailsActivity.this, "Failed to retrieve", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go to previous activity when back button of actionbar is clicked
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}