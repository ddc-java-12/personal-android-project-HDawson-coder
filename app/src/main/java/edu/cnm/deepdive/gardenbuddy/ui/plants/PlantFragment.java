package edu.cnm.deepdive.gardenbuddy.ui.plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.gardenbuddy.R;

public class PlantFragment extends Fragment {

  private PlantViewModel galleryViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    galleryViewModel =
        ViewModelProviders.of(this).get(PlantViewModel.class);
    View root = inflater.inflate(R.layout.fragment_plants, container, false);
    final TextView textView = root.findViewById(R.id.plant_list);
    galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}