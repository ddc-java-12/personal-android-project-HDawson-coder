package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

/**
 * Plant Entity is the parent entity to History and Note.
 * Every Note or History created is assigned to a plantId.
 * The Plant entity currently has 4 pre-loaded plants, but will eventually have a much more
 * robust list of plants.
 */
@Entity
public class Plant {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "plant_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  @NonNull
  @ColumnInfo(index = true)
  private String commonName;

  @NonNull
  private String scientificName;

  @NonNull
  private Integer minTemp;

  @NonNull
  private Integer maxTemp;

  @NonNull
  private Integer daysToMaturity;

  @NonNull
  private Integer spacingInInches;

  /**
   * Gets the unique identifier for the plant.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the plant.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the timestamp of when the Plant is created.
   * @return
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Returns the timestamp of when the Plant is created.
   * @param timestamp
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }
/**
 * Gets the commonName of the Plant.
 */
  @NonNull
  public String getCommonName() {
    return commonName;
  }

  /**
   * Returns the commonName of the Plant.
   * @param commonName
   */
  public void setCommonName(@NonNull String commonName) {
    this.commonName = commonName;
  }

  /**
   * Gets the scientificName of the Plant.
   * @return
   */
  @NonNull
  public String getScientificName() {
    return scientificName;
  }

  /**
   * Returns the scientificName of the Plant.
   * @param scientificName
   */
  public void setScientificName(@NonNull String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * Gets the minimum temperature of soil for when the plant can be planted.
   * @return
   */
  @NonNull
  public Integer getMinTemp() {
    return minTemp;
  }

  /**
   * Returns the minimum temperature of soil for when the plant can be planted.
   * @param minTemp
   */
  public void setMinTemp(@NonNull Integer minTemp) {
    this.minTemp = minTemp;
  }

  /**
   * Gets the maximum temperature of soil for when the plant can be planted.
   * @return
   */
  @NonNull
  public Integer getMaxTemp() {
    return maxTemp;
  }

  /**
   * Returns the maximum temperature of soil for when the plant can be planted.
   * @param maxTemp
   */
  public void setMaxTemp(@NonNull Integer maxTemp) {
    this.maxTemp = maxTemp;
  }

  /**
   * Gets the number of average days a plant will take the mature.
   * @return
   */
  @NonNull
  public Integer getDaysToMaturity() {
    return daysToMaturity;
  }

  /**
   * Returns the number of average days a plant will take the mature.
   * @param daysToMaturity
   */
  public void setDaysToMaturity(@NonNull Integer daysToMaturity) {
    this.daysToMaturity = daysToMaturity;
  }

  /**
   * Gets the spacingInInches that a plant must have to thrive.
   * @return
   */
  @NonNull
  public Integer getSpacingInInches() {
    return spacingInInches;
  }

  /**
   * Returns he spacingInInches that a plant must have to thrive.
   * @param spacingInInches
   */
  public void setSpacingInInches(@NonNull Integer spacingInInches) {
    this.spacingInInches = spacingInInches;
  }

  @NonNull
  @Override
  public String toString() {
    return commonName;
  }
}


