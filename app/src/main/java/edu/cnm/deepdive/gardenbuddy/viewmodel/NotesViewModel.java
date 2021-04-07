package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;

public class NotesViewModel extends AndroidViewModel implements LifecycleObserver {

  private final PlantRepository plantRepository;

  public NotesViewModel(@NonNull Application application) {
    super(application);
    plantRepository = new PlantRepository(application);
  }

  public PlantRepository getPlantRepository() {
    return plantRepository;
  }

}