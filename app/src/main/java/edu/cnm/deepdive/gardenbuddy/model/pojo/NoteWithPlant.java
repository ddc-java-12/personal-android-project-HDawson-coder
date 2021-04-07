package edu.cnm.deepdive.gardenbuddy.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import java.util.LinkedList;
import java.util.List;

public class NoteWithPlant extends Note {

  @Relation(
      entity = Note.class,
      parentColumn = "note_id",
      entityColumn = "note_id"
  )
  private Plant plant;

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

}
