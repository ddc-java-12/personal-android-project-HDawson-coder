package edu.cnm.deepdive.gardenbuddy.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.gardenbuddy.databinding.ActivityMainBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

//  private ActivityMainBinding binding;
//  private MainActivityDrawer drawerActivity;
//  private AppBarConfiguration appBarConfiguration;
//  private NavController navController;
//  private DrawerLayout drawerLayout;
//
//    @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    binding = ActivityMainBinding.inflate(getLayoutInflater());
//    setContentView(binding.getRoot());
//    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//    viewModel.getPlants().observe(this, (plants) -> {
//      ArrayAdapter<Plant> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plants);
//      binding.plantList.setAdapter(adapter);
//    });
//    binding.addPlant.setOnClickListener((v) -> {
//      Plant plant = new Plant();
//      plant.setCommonName(binding.commonName.getText().toString().trim());
//      plant.setMinTemp(Integer.parseInt(binding.minTemp.getText().toString().trim()));
//      plant.setMaxTemp(Integer.parseInt(binding.maxTemp.getText().toString().trim()));
//      viewModel.save(plant);
//    });
//  }
//

}