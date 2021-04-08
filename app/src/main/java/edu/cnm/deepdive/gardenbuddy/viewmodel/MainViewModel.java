package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {



  private final MutableLiveData<Note> note;
  private final MutableLiveData<Plant> plant;
  private final MutableLiveData<List<Plant>> plants;
  private final MutableLiveData<Throwable> throwable;



  public MainViewModel(@NotNull Application application) {
    super(application);
    plant = new MutableLiveData<>();
    throwable = new MutableLiveData<>();

    note = new MutableLiveData<>();
    plants = new MutableLiveData<>();
  }

  public LiveData<Plant> getPlant() {
    return plant;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Plant>> loadPlants() {
    return null;

  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}
