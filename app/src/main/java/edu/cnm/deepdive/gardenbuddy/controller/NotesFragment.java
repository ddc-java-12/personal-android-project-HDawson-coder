package edu.cnm.deepdive.gardenbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenOtherNote;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenPestNote;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenWeatherNote;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentNotesBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.viewmodel.MainViewModel;
import edu.cnm.deepdive.gardenbuddy.viewmodel.NotesViewModel;
import java.util.List;
import java.util.Observable;
import org.jetbrains.annotations.NotNull;

/**
 * This is the notes fragment which will show all categories of notes.
 */

public class NotesFragment extends Fragment {

  private NotesViewModel notesViewModel;
  private FragmentNotesBinding binding;
  private List<Plant> plants;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNotesBinding.inflate(inflater, container, false);
    MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    mainViewModel.loadPlants().observe(getViewLifecycleOwner(), (plants) -> {
      this.plants = plants;
    ArrayAdapter<Plant> adapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner, plants);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
    binding.plantsSpinner.setAdapter(adapter);
    //TODO Attach event handler to spinner. To get a different list of notes for plants.
    });
    binding.addPestNote.setOnClickListener((value) -> {
      OpenPestNote action = PestNoteFragmentDirections.openPestNote(value.getId());
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    binding.addWeatherNote.setOnClickListener((value) -> {
      OpenWeatherNote action = WeatherNoteFragmentDirections.openWeatherNote(value.getId());
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    binding.addOtherNote.setOnClickListener((value) -> {
      OpenOtherNote action = OtherNoteFragmentDirections.openOtherNote(value.getId());
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    notesViewModel = new ViewModelProvider(getActivity()).get(NotesViewModel.class);
    getLifecycle().addObserver(notesViewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    notesViewModel.getPlants().observe(lifecycleOwner,
        (plants) -> binding.getRoot());
    notesViewModel.getThrowable().observe(lifecycleOwner, (throwable) -> {
      if (throwable != null) {
        Snackbar.make(getContext(), binding.getRoot(), throwable.getMessage(),
            BaseTransientBottomBar.LENGTH_INDEFINITE).show();
      }
    });
    //TODO observe data from viewmodel.
  }
}