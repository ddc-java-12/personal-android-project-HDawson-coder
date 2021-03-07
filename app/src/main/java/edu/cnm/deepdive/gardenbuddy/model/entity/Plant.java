package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Plant {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "plant_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  @ColumnInfo(name = "plant_id", index = true)
  private String commonName;

  private String scientificName;

  private String minTemp;

  private String maxTemp;

  private int daysToMaturity;

  private int spacingInInches;

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

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public String getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(String minTemp) {
    this.minTemp = minTemp;
  }

  public String getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(String maxTemp) {
    this.maxTemp = maxTemp;
  }

  public int getDaysToMaturity() {
    return daysToMaturity;
  }

  public void setDaysToMaturity(int daysToMaturity) {
    this.daysToMaturity = daysToMaturity;
  }

  public int getSpacingInInches() {
    return spacingInInches;
  }

  public void setSpacingInInches(int spacingInInches) {
    this.spacingInInches = spacingInInches;
  }

}


