package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
        foreignKeys = {
            @ForeignKey(
                entity = Plant.class,
                parentColumns = "plant_id", childColumns = "plant_id",
                onDelete = ForeignKey.CASCADE
            )
        }
    )
public class History {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "history_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  @ColumnInfo(name = "plant_id", index = true)
  private long plantId;

  @NonNull
  private Date yearPlanted;

  private int totalPlanted;

  private int totalFruitYielded;

  @NonNull
  private Date dayPlanted;

  @NonNull
  private Date dayFirstHarvest;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  public long getPlantId() {
    return plantId;
  }

  public void setPlantId(long plantId) {
    this.plantId = plantId;
  }

  @NonNull
  public Date getYearPlanted() {
    return yearPlanted;
  }

  public void setYearPlanted(@NonNull Date yearPlanted) {
    this.yearPlanted = yearPlanted;
  }

  public int getTotalPlanted() {
    return totalPlanted;
  }

  public void setTotalPlanted(int totalPlanted) {
    this.totalPlanted = totalPlanted;
  }

  public int getTotalFruitYielded() {
    return totalFruitYielded;
  }

  public void setTotalFruitYielded(int totalFruitYielded) {
    this.totalFruitYielded = totalFruitYielded;
  }

  @NonNull
  public Date getDayPlanted() {
    return dayPlanted;
  }

  public void setDayPlanted(@NonNull Date dayPlanted) {
    this.dayPlanted = dayPlanted;
  }

  @NonNull
  public Date getDayFirstHarvest() {
    return dayFirstHarvest;
  }

  public void setDayFirstHarvest(@NonNull Date dayFirstHarvest) {
    this.dayFirstHarvest = dayFirstHarvest;
  }
}
