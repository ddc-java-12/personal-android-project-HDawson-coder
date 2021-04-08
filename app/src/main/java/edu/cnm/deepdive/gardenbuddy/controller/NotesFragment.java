package edu.cnm.deepdive.gardenbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenOtherNote;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenPestNote;
import edu.cnm.deepdive.gardenbuddy.MobileNavigationDirections.OpenWeatherNote;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.adapter.PestAdapter;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentNotesBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.viewmodel.NotesViewModel;
import java.util.List;

/**
 * This is the notes fragment which will show all categories of notes.
 */

public class NotesFragment extends Fragment {

  private NotesViewModel notesViewModel;
  private FragmentNotesBinding binding;
  private List<Plant> plants;
  private PestAdapter pestAdapter;

  /**
   *
   * @param inflater provides the layout fragment for Notes Fragment and and inflates to display with UI
   * @param container provides the container for the ViewGroup of NotesFragment to display.
   * @param savedInstanceState provides the information from the database and utilizes where needed.
   * @return provides the viewModel roots to display.
   */

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentNotesBinding.inflate(inflater, container, false);
    binding.addPestNote.setOnClickListener((value) -> {
      long plantId = ((Plant) binding.plantsSpinner.getSelectedItem()).getId();
      OpenPestNote action = PestNoteFragmentDirections.openPestNote(plantId);
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    binding.addWeatherNote.setOnClickListener((value) -> {
      long plantId = ((Plant) binding.plantsSpinner.getSelectedItem()).getId();
      OpenWeatherNote action = WeatherNoteFragmentDirections.openWeatherNote(plantId);
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    binding.addOtherNote.setOnClickListener((value) -> {
      long plantId = ((Plant) binding.plantsSpinner.getSelectedItem()).getId();
      OpenOtherNote action = OtherNoteFragmentDirections.openOtherNote(plantId);
      Navigation.findNavController(binding.getRoot()).navigate(action);
    });
    binding.plantsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Plant plant = (Plant) parent.getItemAtPosition(position);
        notesViewModel.setPlantId(plant.getId());
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });
    return binding.getRoot();
  }

  /**
   *
   * @param view Gathers the information from the ViewModel to display as it changes during application use.
   * @param savedInstanceState
   */
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    notesViewModel = new ViewModelProvider(getActivity()).get(NotesViewModel.class);
    getLifecycle().addObserver(notesViewModel);
    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    notesViewModel.getPlants().observe(lifecycleOwner, (plants) -> {
      this.plants = plants;
      ArrayAdapter<Plant> adapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner, plants);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
      binding.plantsSpinner.setAdapter(adapter);

    });
    notesViewModel.getPestNotes().observe(lifecycleOwner, (notes) -> {
      binding.pestNotes.setAdapter(new PestAdapter(getContext(), notes));
    });
    notesViewModel.getThrowable().observe(lifecycleOwner, (throwable) -> {
      if (throwable != null) {
        Snackbar.make(getContext(), binding.getRoot(), throwable.getMessage(),
            BaseTransientBottomBar.LENGTH_INDEFINITE).show();
      }
    });
    //TODO observe data from viewmodel.
  }
}