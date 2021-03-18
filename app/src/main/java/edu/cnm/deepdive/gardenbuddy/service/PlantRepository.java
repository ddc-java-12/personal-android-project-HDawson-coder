package edu.cnm.deepdive.gardenbuddy.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.HistoryDao;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.NoteDao;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.PlantDao;
import edu.cnm.deepdive.gardenbuddy.model.entity.pojo.PlantWithHistory;
import edu.cnm.deepdive.gardenbuddy.model.entity.pojo.PlantWithNotes;
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

  public Single<PlantWithHistory> save(PlantWithHistory plant) {
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
            for (History history : plant.getHistories()) {
              history.setPlantId(plantId);
            }
            return historyDao.insert(plant.getHistories());
          })
          .map((historyIds) -> {
            Iterator<Long> idIterator = historyIds.iterator();
            Iterator<History> wagerIterator = plant.getHistories().iterator();
            while (idIterator.hasNext() && wagerIterator.hasNext()) {
              wagerIterator.next().setId(idIterator.next());
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

    public LiveData<PlantWithHistory> get(long plantId) {
    return plantDao.selectByHstId(plantId);
    }

  }
