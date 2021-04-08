package edu.cnm.deepdive.gardenbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.PlantRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class NotesViewModel extends AndroidViewModel implements LifecycleObserver {

  private final PlantRepository plantRepository;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Long> plantId;
  private final LiveData<List<Note>> pestNotes;
  private final LiveData<List<Note>> weatherNotes;
  private final LiveData<List<Note>> otherNotes;
  private final CompositeDisposable pending;

  public NotesViewModel(@NonNull Application application) {
    super(application);
    plantRepository = new PlantRepository(application);
    throwable = new MutableLiveData<>();
    plantId = new MutableLiveData<>();
    pending = new CompositeDisposable();
    pestNotes = Transformations.switchMap(plantId, (id) -> plantRepository.getNotesByCategory(id, Category.PEST));
    weatherNotes = Transformations.switchMap(plantId, (id) -> plantRepository.getNotesByCategory(id, Category.WEATHER));
    otherNotes = Transformations.switchMap(plantId, (id) -> plantRepository.getNotesByCategory(id, Category.OTHER));
  }

  public LiveData<List<Plant>> getPlants() {
    return plantRepository.getPlants();
  }

  public void setPlantId(long id) {
    plantId.setValue(id);
  }

  public void saveNote(Note note) {
    pending.add(
        plantRepository
            .saveNote(note)
            .subscribe(
                (n) -> {
                }, throwable::postValue
            ));
  }

  public LiveData<List<Note>> getPestNotes() {
    return pestNotes;
  }

  public LiveData<List<Note>> getWeatherNotes() {
    return weatherNotes;
  }

  public LiveData<List<Note>> getOtherNotes() {
    return otherNotes;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}