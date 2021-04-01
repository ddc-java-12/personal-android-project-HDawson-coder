package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
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
public class Note {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "note_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Date timestamp = new Date();

  @ColumnInfo(name = "plant_id", index = true)
  private long plantId;

  @NonNull
  @ColumnInfo(index = true)
  private Category category;

  private String note;

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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public enum Category {
    PEST,
    WEATHER,
    OTHER;

    @TypeConverter
    public static Integer categoryToInteger(Category value) {
      return (value != null) ? value.ordinal() : null;
    }

    @TypeConverter
    public static Category integerToCategory(Integer value) {
      return (value != null) ? Category.values()[value] : null;
    }
  }
}
