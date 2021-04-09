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

/**
 * NotesViewModel gathers everything needed to display on the notes Fragment when the notes page is
 * loaded and displayed.
 */
public class NotesViewModel extends AndroidViewModel implements LifecycleObserver {

  private final PlantRepository plantRepository;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Long> plantId;
  private final LiveData<List<Note>> pestNotes;
  private final LiveData<List<Note>> weatherNotes;
  private final LiveData<List<Note>> otherNotes;
  private final CompositeDisposable pending;

  /**
   * All of the fields constructors that are needed for the ViewModels.
   *
   * @param application
   */
  public NotesViewModel(@NonNull Application application) {
    super(application);
    plantRepository = new PlantRepository(application);
    throwable = new MutableLiveData<>();
    plantId = new MutableLiveData<>();
    pending = new CompositeDisposable();
    pestNotes = Transformations.switchMap(plantId, (id) ->
        plantRepository.getNotesByCategory(id, Category.PEST));
    weatherNotes = Transformations.switchMap(plantId, (id) ->
        plantRepository.getNotesByCategory(id, Category.WEATHER));
    otherNotes = Transformations.switchMap(plantId, (id) ->
        plantRepository.getNotesByCategory(id, Category.OTHER));
  }

  /**
   * Gets the plants from the plantRepository to use for the page.
   *
   * @return
   */
  public LiveData<List<Plant>> getPlants() {
    return plantRepository.getPlants();
  }

  /**
   * Sets the plantId so the connected entities will know to be displayed as well.
   * @param id
   */
  public void setPlantId(long id) {
    plantId.setValue(id);
  }

  /**
   * Saves a note when the user inputs it through this application page.
   * @param note An object from the Note class.
   */
  public void saveNote(Note note) {
    pending.add(
        plantRepository
            .saveNote(note)
            .subscribe(
                (n) -> {
                }, throwable::postValue
            ));
  }

  /**
   * Gets all Notes from the Pest Category for use in the ViewModel.
   * @return
   */
  public LiveData<List<Note>> getPestNotes() {
    return pestNotes;
  }

  /**
   * Gets all Notes from the Weather Category for use in the ViewModel.
   * @return
   */
  public LiveData<List<Note>> getWeatherNotes() {
    return weatherNotes;
  }

  /**
   * Gets all Notes from the Other Category for use in the ViewModel.
   * @return
   */
  public LiveData<List<Note>> getOtherNotes() {
    return otherNotes;
  }

  /**
   * Allows the ViewModel to not display anything if there is nothing to display.
   * @return
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}