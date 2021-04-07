package edu.cnm.deepdive.gardenbuddy.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
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


public class PlantRepository {

  private final Context context;
  private final PlantDao plantDao;
  private final NoteDao noteDao;
  private final HistoryDao historyDao;

  public PlantRepository(Context context) {
    this.context = context;
    GardenBuddyDatabase database = GardenBuddyDatabase.getInstance();
    plantDao = database.getPlantDao();
    noteDao = database.getNoteDao();
    historyDao = database.getHistoryDao();
  }

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

  public LiveData<List<Plant>> getAll() {
    return plantDao.selectAll();
  }

//  public LiveData<PlantWithHistories> get(long plantId) {
//    return plantDao.selectByHstId(plantId);
//  }

  public Completable newPlant() {
    return Single.fromCallable(() -> {
      Plant plant = new Plant();
      plant.setCommonName("Corn");
      plant.setMaxTemp(50);
      plant.setMinTemp(40);
      return plant;
    }).subscribeOn(Schedulers.computation()).flatMap(plantDao::insert).ignoreElement()
        .subscribeOn(Schedulers.io());
  }

  public LiveData<List<Note>> getNotesByCategory(Plant plant, Category category) {
    return noteDao.selectByPlantAndCategory(plant.getId(), category);
  }
}
