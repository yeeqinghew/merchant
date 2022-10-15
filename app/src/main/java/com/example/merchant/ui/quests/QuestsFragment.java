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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.merchant.Quest;
import com.example.merchant.QuestAdapter;
import com.example.merchant.R;
import com.example.merchant.databinding.FragmentQuestsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class QuestsFragment extends Fragment {

    private FragmentQuestsBinding binding;
    // action bar
    private ActionBar actionBar;
    // firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    // quests view
    private View QuestsView;
    private RecyclerView questsList;
    QuestAdapter questAdapter;

    public QuestsFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        com.example.merchant.ui.quests.QuestsViewModel questsViewModel =
                new ViewModelProvider(this).get(com.example.merchant.ui.quests.QuestsViewModel.class);

        binding = FragmentQuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // configure action bar, title
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Quests");

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Quest Recycler View
        questsList = (RecyclerView) root.findViewById(R.id.questsRv);
        questsList.setLayoutManager(new LinearLayoutManager(getContext()));

        reference = FirebaseDatabase.getInstance().getReference().child("Quest");

        FirebaseRecyclerOptions<Quest> options =
                new FirebaseRecyclerOptions.Builder<Quest>()
                        .setQuery(reference, Quest.class).build();

        questAdapter = new QuestAdapter(options);
        questsList.setAdapter(questAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        questAdapter.startListening();
        // Remove crash on press back
        questsList.getRecycledViewPool().clear();
        questAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        questAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}