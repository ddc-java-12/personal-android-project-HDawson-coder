package edu.cnm.deepdive.gardenbuddy.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import java.util.LinkedList;
import java.util.List;

/**
 * All Notes must be associated to a Plant.
 */
public class PlantWithNotes extends Plant {

  @Relation(
      entity = Note.class,
      parentColumn = "plant_id",
      entityColumn = "plant_id"
  )
  private List<Note> notes = new LinkedList<>();

  /**
   * Gets the list of Notes and returns the notes.
   * @return
   */
  @NonNull
  public List<Note> getNotes() {
    return notes;
  }

  /**
   * Sets the list of Notes.
   * @param notes
   */
  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }
}
