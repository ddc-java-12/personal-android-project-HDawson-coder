package edu.cnm.deepdive.gardenbuddy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface NoteDao {

  @Insert
  Single<Long> insert(Note note);

  @Insert
  Single<List<Long>> insert(Note... notes);

  @Insert
  Single<List<Long>> insert(Collection<Note> notes);

  @Update
  Single<Integer> update(Note note);

  @Update
  Single<Integer> update(Note... notes);

  @Update
  Single<Integer> update(Collection<Note> notes);

  @Delete
  Single<Integer> delete(Note note); // a single history ?

  @Delete
  Single<Integer> delete(Note... note); // a whole bunch of History's

  @Delete
  Single<Integer> delete(Collection<Note> note); // a collection of history's

  @Query("SELECT * FROM Note WHERE plant_id = :plantId")
  LiveData<List<Note>> selectByPlant(long plantId);

  @Query("SELECT * FROM Note WHERE plant_id = :plantId AND category = :category")
  LiveData<List<Note>> selectByPlantAndCategory(long plantId, Category category);

}

