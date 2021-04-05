package edu.cnm.deepdive.gardenbuddy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.model.pojo.PlantWithHistories;
import edu.cnm.deepdive.gardenbuddy.model.pojo.PlantWithNotes;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface PlantDao {

  @Insert
  Single<Long> insert(Plant plant);

  @Insert
  Single<List<Long>> insert(Collection<Plant> plants);

  @Insert
  Single<List<Long>> insert (Plant... plants);

  @Update
  Single<Integer> update(Plant plant);

  @Update
  Single<Integer> update(Plant... plants);

  @Update
  Single<Integer> update(Collection<Plant> plants);

  @Delete
  Single<Integer> delete(Plant plant); // a single plant ?

  @Delete
  Single<Integer> delete(Plant... plant); // a whole bunch of plants

  @Delete
  Single<Integer> delete(Collection<Plant> plant); // a collection of plants

  @Query("SELECT * FROM Plant ORDER BY timestamp DESC")
  LiveData<List<Plant>> selectAll();

  @Transaction
  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  LiveData<PlantWithHistories> selectByHstId(long plantId);

  @Transaction
  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  LiveData<PlantWithNotes> selectByNoteId(long plantId);

}

