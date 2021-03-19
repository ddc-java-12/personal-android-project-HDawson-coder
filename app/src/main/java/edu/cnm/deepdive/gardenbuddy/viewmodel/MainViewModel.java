package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
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

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void loadPlant() {
    throwable.setValue(null);
    repository.newPlant()
        .subscribe(
            this::loadPlant,
            throwable::postValue
        );
  }
}
