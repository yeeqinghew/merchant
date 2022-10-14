package com.example.merchant.ui.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merchant.Project;
import com.example.merchant.ProjectAdapter;
import com.example.merchant.R;
import com.example.merchant.databinding.FragmentProjectsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProjectsFragment extends Fragment {

    private FragmentProjectsBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    // projects view
    private RecyclerView projectsList;
    ProjectAdapter projectAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProjectsViewModel projectsViewModel = new ViewModelProvider(this).get(ProjectsViewModel.class);
        binding = FragmentProjectsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       projectsList = (RecyclerView) root.findViewById(R.id.projectList);
       projectsList.setLayoutManager(new LinearLayoutManager(getContext()));
       firebaseDatabase = FirebaseDatabase.getInstance();

       getAllProjects();

       return root;
    }

    private void getAllProjects() {
        reference = firebaseDatabase.getReference().child("Project");
        FirebaseRecyclerOptions<Project> options = new FirebaseRecyclerOptions.Builder<Project>().setQuery(reference, Project.class).build();
        projectAdapter = new ProjectAdapter(options);
        projectsList.setAdapter(projectAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        projectAdapter.startListening();
        // Remove crash on press back
        projectsList.getRecycledViewPool().clear();
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        projectAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}