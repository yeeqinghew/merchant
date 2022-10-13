package com.example.merchant.ui.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.merchant.databinding.FragmentProjectsBinding;

public class ProjectsFragment extends Fragment {

private FragmentProjectsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ProjectsViewModel projectsViewModel =
                new ViewModelProvider(this).get(ProjectsViewModel.class);

    binding = FragmentProjectsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textProjects;
        projectsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}