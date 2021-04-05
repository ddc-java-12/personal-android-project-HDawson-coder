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

  @ColumnInfo(index = true)
  private String commonName;

  private String scientificName;

  private Integer minTemp;

  private Integer maxTemp;

  private Integer daysToMaturity;

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

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public Integer getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(Integer minTemp) {
    this.minTemp = minTemp;
  }

  public Integer getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(Integer maxTemp) {
    this.maxTemp = maxTemp;
  }

  public Integer getDaysToMaturity() {
    return daysToMaturity;
  }

  public void setDaysToMaturity(Integer daysToMaturity) {
    this.daysToMaturity = daysToMaturity;
  }

  public Integer getSpacingInInches() {
    return spacingInInches;
  }

  public void setSpacingInInches(Integer spacingInInches) {
    this.spacingInInches = spacingInInches;
  }

  @NonNull
  @Override
  public String toString() {
    return commonName;
  }
}


