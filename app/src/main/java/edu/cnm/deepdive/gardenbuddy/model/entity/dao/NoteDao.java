package edu.cnm.deepdive.gardenbuddy.model.entity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao // One Dao per entity
public interface NoteDao {

  @Insert
  Single<Long> insert(Note note);

  @Update
  Single<Integer> update(Note note);

  @Delete
  Single<Integer> delete(Note note); // a single history ?

  @Delete
  Single<Integer> delete(Note... note); // a whole bunch of History's

  @Delete
  Single<Integer> delete(Collection<Note> note); // a collection of history's

  @Query("SELECT * FROM Note ORDER BY timestamp DESC")
  LiveData<List<Note>> selectAll();

//  @Transaction
//  @Query("SELECT * FROM History WHERE history_id = :noteId")
//  LiveData<SpinWithWagers> selectById(long noteId);
//}  // TODO figure out how POJO will work with Garden Buddy

}
