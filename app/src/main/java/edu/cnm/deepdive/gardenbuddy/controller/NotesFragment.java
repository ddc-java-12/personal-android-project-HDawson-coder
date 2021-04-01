package edu.cnm.deepdive.gardenbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentNotesBinding;
import edu.cnm.deepdive.gardenbuddy.viewmodel.NotesViewModel;
import org.jetbrains.annotations.NotNull;

public class NotesFragment extends Fragment {

  private NotesViewModel notesViewModel;
  private FragmentNotesBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNotesBinding.inflate(inflater, container, false);
    //TODO Attach event handler to spinner. To get a different list of notes for plants.
    //TODO Attach event handlers to floating action buttons.
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    getLifecycle().addObserver(notesViewModel);
    //TODO observe data from viewmodel.
  }
}