package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
import java.util.List;

public class NotesViewModel extends AndroidViewModel implements LifecycleObserver {

  private final PlantRepository plantRepository;
  private final MutableLiveData<List<Plant>> plants;
  private final MutableLiveData<Throwable> throwable;

  public NotesViewModel(@NonNull Application application) {
    super(application);
    plantRepository = new PlantRepository(application);
    throwable = new MutableLiveData<>();
    plants = new MutableLiveData<>();
  }

  public PlantRepository getPlantRepository() {
    return plantRepository;
  }

  public MutableLiveData<List<Plant>> getPlants() {
    return plants;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}