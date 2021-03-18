package edu.cnm.deepdive.gardenbuddy.model.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.pojo.PlantWithHistory;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface HistoryDao {

  @Insert
  Single<Long> insert(History history);

  @Insert
  Single<Long> insert(History... histories);

  @Insert
  Single<List<Long>> insert(Collection<History> histories);


  @Update
  Single<Integer> update(History history);

  @Delete
  Single<Integer> delete(History history); // a single history ?

  @Delete
  Single<Integer> delete(History... history); // a whole bunch of History's

  @Delete
  Single<Integer> delete(Collection<History> history); // a collection of history's

  @Transaction
  @Query("SELECT * FROM History WHERE history_id = :historyId")
  LiveData<PlantWithHistory> selectByPlant(long historyId);

}
