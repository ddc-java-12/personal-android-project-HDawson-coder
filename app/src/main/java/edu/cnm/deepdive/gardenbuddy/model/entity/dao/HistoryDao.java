package edu.cnm.deepdive.gardenbuddy.model.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface HistoryDao {

  @Insert
  Single<Long> insert(History history);

  @Update
  Single<Integer> update(History history);

  @Delete
  Single<Integer> delete(History history); // a single history ?

  @Delete
  Single<Integer> delete(History... history); // a whole bunch of History's

  @Delete
  Single<Integer> delete(Collection<History> history); // a collection of history's

  @Query("SELECT * FROM History ORDER BY timestamp DESC")
  LiveData<List<History>> selectAll();

//  @Transaction
//  @Query("SELECT * FROM History WHERE history_id = :historyId")
//  LiveData<SpinWithWagers> selectById(long historyId);
//}  // TODO figure out how POJO will work with Garden Buddy

}
