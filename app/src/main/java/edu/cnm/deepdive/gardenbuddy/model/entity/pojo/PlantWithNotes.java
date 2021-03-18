package edu.cnm.deepdive.gardenbuddy.model.entity.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import java.util.LinkedList;
import java.util.List;

public class PlantWithNotes extends Plant {

  @Relation(
      entity = Note.class,
      parentColumn = "plant_id",
      entityColumn = "plant_id"
  )
  private List<Note> notes = new LinkedList<>();

  @NonNull
  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }
}
