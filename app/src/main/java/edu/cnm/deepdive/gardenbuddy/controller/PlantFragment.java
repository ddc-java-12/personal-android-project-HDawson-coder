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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentPlantsBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.viewmodel.PlantViewModel;
import java.util.List;

/**
 * PlantFragment is currently a placeholder page. Eventually this will display all plants in the
 * database along with important information such as Name, when to plant, etc...
 */
public class PlantFragment extends Fragment {

  private static final int FULL_WIDTH = 6;

  private PlantViewModel plantViewModel;
  private FragmentPlantsBinding binding;

  /**
   * This currently creates the view of the PlantFragment page from the Activity Drawer when selected.
   * Currently it is just a page that displays some pictures.
   * @param inflater Needed to display the layout for the PlantFragment.
   * @param container Needed to get the ViewGroup for PlantFragment
   * @param savedInstanceState
   * @return Returns the root to display the PlantFragment.
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    plantViewModel = ViewModelProviders.of(this).get(PlantViewModel.class);
    View root = inflater.inflate(R.layout.fragment_plants, container, false);
    final TextView textView = root.findViewById(R.id.plants_header);
    plantViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {


      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
/**
 * Attempting to start Plants to show on grid layout.
 */
//  private void setupAdapter(List<Plant> spots) {
//    GridLayoutManager layoutManager =
//        new GridLayoutManager(getContext(), FULL_WIDTH, LinearLayoutManager.VERTICAL, false);
//    layoutManager.setSpanSizeLookup(new PlantSpanLookup(spots));
//    binding.pestNotes
//
//  }
//
//  private static class PlantSpanLookup extends SpanSizeLookup {
//
//    private final List<Plant> plantSpots;
//
//    private PlantSpanLookup(List<Plant> plantSpots) {
//      this.plantSpots = plantSpots;
//    }
//
//    @Override
//    public int getSpanSize(int position) {
//      return Integer.parseInt(plantSpots.get(position).getCommonName());
//    }

}