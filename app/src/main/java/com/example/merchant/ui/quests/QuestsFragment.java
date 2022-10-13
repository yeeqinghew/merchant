package com.example.merchant.ui.quests;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.merchant.LoginActivity;
import com.example.merchant.Merchant;
import com.example.merchant.databinding.FragmentQuestsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestsFragment extends Fragment {

    private FragmentQuestsBinding binding;
    // action bar
    private ActionBar actionBar;
    // firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        com.example.merchant.ui.quests.QuestsViewModel questsViewModel =
                new ViewModelProvider(this).get(com.example.merchant.ui.quests.QuestsViewModel.class);

    binding = FragmentQuestsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

//        final TextView textView = binding.textQuests;
//        questsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // configure action bar, title
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Quests");

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
        return root;
    }

    private void checkUser() {
        // get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            // user not logged in, move to login
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
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

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}