package com.example.merchant.ui.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merchant.CreateEvent;
import com.example.merchant.R;
import com.example.merchant.databinding.FragmentEventsBinding;
import com.example.merchant.EventAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private FragmentEventsBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    // events view
    private View EventsView;
    private RecyclerView eventsList;
    EventAdapter eventAdapter;


    public EventsFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventsViewModel eventsViewModel = new ViewModelProvider(this).get(EventsViewModel.class);
        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        eventsList = (RecyclerView) root.findViewById(R.id.eventList);
        eventsList.setLayoutManager((new LinearLayoutManager(getContext())));
        firebaseDatabase = FirebaseDatabase.getInstance();

        getAllEvents();

        return root;
    }

    private void getAllEvents() {
        reference = firebaseDatabase.getReference().child("CreateEvent");
        FirebaseRecyclerOptions<CreateEvent> options = new FirebaseRecyclerOptions.Builder<CreateEvent>().setQuery(reference, CreateEvent.class).build();
        eventAdapter = new EventAdapter(options);
        eventsList.setAdapter(eventAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        eventAdapter.startListening();
        // Remove crash on press back
        eventsList.getRecycledViewPool().clear();
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}