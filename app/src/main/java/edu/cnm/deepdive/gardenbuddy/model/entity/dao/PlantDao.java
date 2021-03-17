package edu.cnm.deepdive.gardenbuddy.model.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface PlantDao {

  @Insert
  Single<Long> insert(Plant plant);

  @Update
  Single<Integer> update(Plant plant);

  @Delete
  Single<Integer> delete(Plant plant); // a single plant ?

  @Delete
  Single<Integer> delete(Plant... plant); // a whole bunch of plants

  @Delete
  Single<Integer> delete(Collection<Plant> plant); // a collection of plants

  @Query("SELECT * FROM Plant ORDER BY timestamp DESC")
  LiveData<List<Plant>> selectAll();

//  @Transaction
//  @Query("SELECT * FROM History WHERE history_id = :plantId")
//  LiveData<SpinWithWagers> selectById(long plantId);
//}  // TODO figure out how POJO will work with Garden Buddy

}