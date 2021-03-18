package edu.cnm.deepdive.gardenbuddy.model.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.pojo.PlantWithHistories;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface HistoryDao {

  @Insert
  Single<Long> insert(edu.cnm.deepdive.gardenbuddy.model.entity.History history);

  @Insert
  Single<List<Long>> insert(edu.cnm.deepdive.gardenbuddy.model.entity.History... histories);

  @Insert
  Single<List<Long>> insert(Collection<edu.cnm.deepdive.gardenbuddy.model.entity.History> histories);

  @Update
  Single<Integer> update(edu.cnm.deepdive.gardenbuddy.model.entity.History history);

  @Update
  Single<Integer> update(edu.cnm.deepdive.gardenbuddy.model.entity.History... histories);

  @Update
  Single<Integer> update(Collection<edu.cnm.deepdive.gardenbuddy.model.entity.History> histories);

  @Delete
  Single<Integer> delete(edu.cnm.deepdive.gardenbuddy.model.entity.History history); // a single history ?

  @Delete
  Single<Integer> delete(edu.cnm.deepdive.gardenbuddy.model.entity.History... history); // a whole bunch of History's

  @Delete
  Single<Integer> delete(Collection<edu.cnm.deepdive.gardenbuddy.model.entity.History> history); // a collection of history's

  @Transaction
  @Query("SELECT * FROM History WHERE plant_id = :plantId")
  LiveData<PlantWithHistories> selectByPlant(long plantId);

}
