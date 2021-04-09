package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * The plantId is the foreign key used with the History Database.
 * This is the entity class for the History Database. The HistoryDatabase is not yet implemented
 * in the application. But will be done so at a later point in app development.
 * There will be a many to one relationship with the Plant Entity.
 */
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

  /**
   * Returns the Unique identifier of this History.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier of this history.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the timestamp of when the History instance is created.
   * @return
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Returns the TimeStamp of when the History instance is created.
   * @param timestamp
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the unique plantId that is connected to the History.
   * @return
   */
  public long getPlantId() {
    return plantId;
  }

  /**
   * Sets the plantId that is connected to the History.
   * @param plantId
   */
  public void setPlantId(long plantId) {
    this.plantId = plantId;
  }

  /**
   * Gets the Date of the year planted for the History created.
   * The year planted will be a date that the user will input themselves.
   * @return
   */
  @NonNull
  public Date getYearPlanted() {
    return yearPlanted;
  }

  /**
   * Sets the date of the year planted after user has input a date.
   * @param yearPlanted
   */
  public void setYearPlanted(@NonNull Date yearPlanted) {
    this.yearPlanted = yearPlanted;
  }

  /**
   * Gets the total amount planted that is input by the user.
   * @return
   */
  public int getTotalPlanted() {
    return totalPlanted;
  }

  /**
   * Sets the total amount planted that is input by the user.
   * @param totalPlanted
   */
  public void setTotalPlanted(int totalPlanted) {
    this.totalPlanted = totalPlanted;
  }

  /**
   * Gets the total amount of fruit yielded that is set by the user.
   * @return
   */
  public int getTotalFruitYielded() {
    return totalFruitYielded;
  }

  /**
   * Sets the total amount of fruit yielded that is input by the user.
   * @param totalFruitYielded
   */
  public void setTotalFruitYielded(int totalFruitYielded) {
    this.totalFruitYielded = totalFruitYielded;
  }

  /**
   * The user is able to detail what day the specific plant is planted.
   * This will get the day planted and return it to the user.
   * @return
   */
  @NonNull
  public Date getDayPlanted() {
    return dayPlanted;
  }

  /**
   * Sets the day planted when user inputs it into the database.
   * @param dayPlanted
   */
  public void setDayPlanted(@NonNull Date dayPlanted) {
    this.dayPlanted = dayPlanted;
  }

  /**
   * The user will be able to specify the date of when they get their first harvest from the
   * plant that is planted. This will return the day that is input by the user.
   * @return
   */
  @NonNull
  public Date getDayFirstHarvest() {
    return dayFirstHarvest;
  }

  /**
   * Sets the day input by the user of the first harvest.
   * @param dayFirstHarvest
   */
  public void setDayFirstHarvest(@NonNull Date dayFirstHarvest) {
    this.dayFirstHarvest = dayFirstHarvest;
  }
}
