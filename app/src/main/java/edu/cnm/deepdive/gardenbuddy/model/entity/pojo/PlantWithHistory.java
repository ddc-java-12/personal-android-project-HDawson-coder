package edu.cnm.deepdive.gardenbuddy.model.entity.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import java.util.LinkedList;
import java.util.List;

public class PlantWithHistory extends Plant {

  @Relation(
      entity = History.class,
      parentColumn = "plant_id",
      entityColumn = "plant_id"
  )
  private List<History> histories = new LinkedList<>();

  @NonNull
  public List<History> getHistories() {
    return histories;
  }

  public void setHistories(List<History> histories) {
    this.histories = histories;
  }
}
