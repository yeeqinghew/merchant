package com.example.merchant.ui.rewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merchant.R;
import com.example.merchant.Reward;
import com.example.merchant.RewardAdapter;
import com.example.merchant.databinding.FragmentRewardsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RewardsFragment extends Fragment {

    RewardAdapter rewardAdapter;
    private FragmentRewardsBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    // rewards view
    private RecyclerView rewardsList;

    public RewardsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RewardsViewModel rewardsViewModel = new ViewModelProvider(this).get(RewardsViewModel.class);
        binding = FragmentRewardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rewardsList = (RecyclerView) root.findViewById(R.id.rewardList);
        rewardsList.setLayoutManager((new LinearLayoutManager(getContext())));
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        getAllRewards();
        return root;
    }

    private void getAllRewards() {
        reference = firebaseDatabase.getReference().child("Rewards");
        FirebaseRecyclerOptions<Reward> options = new FirebaseRecyclerOptions.Builder<Reward>().setQuery(reference, Reward.class).build();
        rewardAdapter = new RewardAdapter(options);
        rewardsList.setAdapter(rewardAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        rewardAdapter.startListening();
        // Remove crash on press back
        rewardsList.getRecycledViewPool().clear();
        rewardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        rewardAdapter.stopListening();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}