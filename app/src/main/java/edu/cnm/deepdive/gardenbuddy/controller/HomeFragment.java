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
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.viewmodel.HomeViewModel;

/**
 * Home fragment is the first page that displays after Google Sign in upon launching of the app.
 * It is just a simple welcome in page app with no interactive buttons aside from opening
 * the activity Drawer.
 */
public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;

  /**
   * Home Fragment is the first page that displays on the top level of the Activity Drawer.
   * @param inflater Needed to display the layout for the HomeFragment.
   * @param container Needed to get the ViewGroup for HomeFragment
   * @param savedInstanceState
   * @return Returns the root to display the HomeFragment.
   */
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    final TextView textView = root.findViewById(R.id.text_home);
    homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}