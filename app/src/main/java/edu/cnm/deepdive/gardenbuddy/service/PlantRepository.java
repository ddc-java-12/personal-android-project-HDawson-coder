package edu.cnm.deepdive.gardenbuddy.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.model.dao.HistoryDao;
import edu.cnm.deepdive.gardenbuddy.model.dao.NoteDao;
import edu.cnm.deepdive.gardenbuddy.model.dao.PlantDao;
import edu.cnm.deepdive.gardenbuddy.model.pojo.PlantWithHistories;
import edu.cnm.deepdive.gardenbuddy.model.pojo.PlantWithNotes;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;

/**
 * The PlantRepository connects the Entities to each other for use. Since the Plant Entity
 * is the parent entities for the History and Notes, there is only this PlantRepository and no other
 * repositories.
 */
public class PlantRepository {

  private final Context context;
  private final PlantDao plantDao;
  private final NoteDao noteDao;
  private final HistoryDao historyDao;

  /**
   * Provides the context of the PlantRepository when called upon in other classes.
   * @param context The Context is the database of the different Dao's.
   */
  public PlantRepository(Context context) {
    this.context = context;
    GardenBuddyDatabase database = GardenBuddyDatabase.getInstance();
    plantDao = database.getPlantDao();
    noteDao = database.getNoteDao();
    historyDao = database.getHistoryDao();
  }

  /**
   * Saves a Single Plant in to the Database if a new one is created.
   * @param plant A parameter of the Plant class.
   * @return Returns the saved plant.
   */
  public Single<Plant> save(Plant plant) {
    return (
        (plant.getId() > 0)
            ? plantDao
            .update(plant)
            .map((ignored) -> plant)
            : plantDao
                .insert(plant)
                .map((id) -> {
                  plant.setId(id);
                  return plant;
                })
    )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Saves a note to the database when a new Note is created.
   * @param note A new note to becreated from the Note class.
   * @return Returns the new note that is created by the user.
   */
  public Single<Note> saveNote(Note note) {
    return (
        (note.getId() > 0)
            ? noteDao
            .update(note)
            .map((ignored) -> note)
            : noteDao
                .insert(note)
                .map((id) -> {
                  note.setId(id);
                  return note;
                })
    )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Saves the new History created to be assigned to the Plant it is to be paired with.
   * @param plant The plant the history is assigned to in the Plant class.
   * @return Returns the Plant and History connected.
   */
  public Single<PlantWithHistories> saveHistory(PlantWithHistories plant) {
    if (plant.getId() > 0) {
      //update
      return plantDao
          .update(plant)
          .map((ignored) -> plant)
          .subscribeOn(Schedulers.io()); //let's us specify the line it should began on
    } else {
      //Insert
      return plantDao
          .insert(plant)
          .flatMap((plantId) -> {
            plant.setId(plantId);
            for (edu.cnm.deepdive.gardenbuddy.model.entity.History history : plant.getHistories()) {
              history.setPlantId(plantId);
            }
            return historyDao.insert(plant.getHistories());
          })
          .map((historyIds) -> {
            Iterator<Long> idIterator = historyIds.iterator();
            Iterator<edu.cnm.deepdive.gardenbuddy.model.entity.History> historyIterator = plant
                .getHistories().iterator();
            while (idIterator.hasNext() && historyIterator.hasNext()) {
              historyIterator.next().setId(idIterator.next());
            }
            return plant;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  /**
   * If a Note is created it must be connected to a plant. This allows the Note to be created
   * and assigned to the Plant.
   * @param plant Assigns the plant instance with the PlantWithNotes POJO.
   * @return Returns the Note with the Plant.
   */
  public Single<PlantWithNotes> saveNote(PlantWithNotes plant) {
    if (plant.getId() > 0) {
      //update
      return plantDao
          .update(plant)
          .map((ignored) -> plant)
          .subscribeOn(Schedulers.io()); //let's us specify the line it should began on
    } else {
      //Insert
      return plantDao
          .insert(plant)
          .flatMap((plantId) -> {
            plant.setId(plantId);
            for (Note note : plant.getNotes()) {
              note.setPlantId(plantId);
            }
            return noteDao.insert(plant.getNotes());
          })
          .map((noteIds) -> {
            Iterator<Long> idIterator = noteIds.iterator();
            Iterator<Note> noteIterator = plant.getNotes().iterator();
            while (idIterator.hasNext() && noteIterator.hasNext()) {
              noteIterator.next().setId(idIterator.next());
            }
            return plant;
          })
          .subscribeOn(Schedulers.io());
    }
  }

  /**
   * Allows a plant to be deleted and deletes any child columns (such as history or notes) along
   * with it.
   * @param plant A plant object created from the Plant class.
   * @return Completes the deletion of the object.
   */
  public Completable delete(Plant plant) { //a plant is a plantWithHistory too
    return (
        (plant.getId() == 0)
            ? Completable.complete() //does nothing
            : plantDao
                .delete(plant)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Gets all plants in the current live Database.
   * @return
   */
  public LiveData<List<Plant>> getPlants() {
    return plantDao.selectAll();
  }

  /**
   * Gets all notes in the current live database and returns them by category.
   * @param plantId Each note must be connected to a plantId.
   * @param category Each note must be connected to a category.
   * @return Returns the notes selected by the plantId and the category.
   */
  public LiveData<List<Note>> getNotesByCategory(long plantId, Category category) {
    return noteDao.selectByPlantAndCategory(plantId, category);
  }
}
