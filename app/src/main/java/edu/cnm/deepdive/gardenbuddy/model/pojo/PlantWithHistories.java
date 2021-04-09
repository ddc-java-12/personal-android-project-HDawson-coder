package edu.cnm.deepdive.gardenbuddy.model.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Relation;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import java.util.LinkedList;
import java.util.List;

/**
 * Attaches all Histories to the Plants.
 */
public class PlantWithHistories extends Plant {

  @Relation(
      entity = edu.cnm.deepdive.gardenbuddy.model.entity.History.class,
      parentColumn = "plant_id",
      entityColumn = "plant_id"
  )
  private List<edu.cnm.deepdive.gardenbuddy.model.entity.History> histories = new LinkedList<>();

  /**
   * Gets the list of Histories associated to a plant and returns them.
   * @return
   */
  @NonNull
  public List<edu.cnm.deepdive.gardenbuddy.model.entity.History> getHistories() {
    return histories;
  }
/**
 * Sets the list of Histories to a plant.
 */
  public void setHistories(List<edu.cnm.deepdive.gardenbuddy.model.entity.History> histories) {
    this.histories = histories;
  }
}
