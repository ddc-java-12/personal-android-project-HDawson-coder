package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.gardenbuddy.model.pojo.PlantWithNotes;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
import java.util.List;

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