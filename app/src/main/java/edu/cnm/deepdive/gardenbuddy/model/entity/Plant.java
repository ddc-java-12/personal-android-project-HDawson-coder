package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

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

  @NonNull
  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(@NonNull String commonName) {
    this.commonName = commonName;
  }

  @NonNull
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(@NonNull String scientificName) {
    this.scientificName = scientificName;
  }

  @NonNull
  public Integer getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(@NonNull Integer minTemp) {
    this.minTemp = minTemp;
  }

  @NonNull
  public Integer getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(@NonNull Integer maxTemp) {
    this.maxTemp = maxTemp;
  }

  @NonNull
  public Integer getDaysToMaturity() {
    return daysToMaturity;
  }

  public void setDaysToMaturity(@NonNull Integer daysToMaturity) {
    this.daysToMaturity = daysToMaturity;
  }

  @NonNull
  public Integer getSpacingInInches() {
    return spacingInInches;
  }

  public void setSpacingInInches(@NonNull Integer spacingInInches) {
    this.spacingInInches = spacingInInches;
  }

  @NonNull
  @Override
  public String toString() {
    return commonName;
  }
}


