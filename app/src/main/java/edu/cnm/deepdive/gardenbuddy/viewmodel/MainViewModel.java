package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {

  private final PlantRepository repository;

  private final MutableLiveData<Plant> plant;
  private final MutableLiveData<Throwable> throwable;


  public MainViewModel( @NotNull Application application) {
    super(application);
    repository = new PlantRepository(application);
    plant = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    loadPlant();
  }

  public PlantRepository getRepository() {
    return repository;
  }

  public LiveData<Plant> getPlant() {
    return plant;
  }

  public LiveData<List<Plant>> getPlants() {
    return repository.getAll();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void save(Plant plant) {
    throwable.setValue(null);
    repository
        .save(plant)
        .subscribe(
            (p) -> {},
            this::postThrowable
        );
  }

  public void loadPlant() {
    throwable.setValue(null);
    repository.newPlant()
        .subscribe(
            () -> {},
            this::postThrowable
        );
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(),throwable.getMessage(),throwable);
    this.throwable.postValue(throwable);
  }
}
