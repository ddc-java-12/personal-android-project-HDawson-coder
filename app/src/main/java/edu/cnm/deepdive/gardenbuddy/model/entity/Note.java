package edu.cnm.deepdive.gardenbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import java.util.Date;

/**
 * The plantId is the foreign key used with the Note Database.
 * The notes entity gets information to be used in the database from user input and each note is
 * assigned to a plant and category.
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

  /**
   * Gets the Unique Id for the note.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the Unique Id for the note.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the timestamp assigned when the note is created.
   * @return
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp of the date when note is created.
   * @param timestamp
   */
  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the PlantId that is assigned with the created note.
   * @return
   */
  public long getPlantId() {
    return plantId;
  }

  /**
   * Returns the PlantId that is assigned with the created note.
   * @param plantId
   */
  public void setPlantId(long plantId) {
    this.plantId = plantId;
  }

  /**
   * Gets the note that is written by the the user.
   * @return
   */
  public String getNote() {
    return note;
  }

  /**
   * Sets the note that is written by the user.
   * @param note
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Gets the category that the note is assigned to.
   * @return
   */
  @NonNull
  public Category getCategory() {
    return category;
  }

  /**
   * Returns the category that the note is assigned to.
   * @param category
   */
  public void setCategory(@NonNull Category category) {
    this.category = category;
  }

  /**
   * Category is an enum that is set up for three different categories; Pest, Weather, and Other.
   * Users can write notes that may pertain to each of these three categories and referance back to
   * the categories and notes when needed.
   */
  public enum Category {
    PEST,
    WEATHER,
    OTHER;

    /**
     * Changes the category name to an Integer.
     * @param value
     * @return
     */
    @TypeConverter
    public static Integer categoryToInteger(Category value) {
      return (value != null) ? value.ordinal() : null;
    }

    /**
     * Changes the integer back to the Category name.
     * @param value
     * @return
     */
    @TypeConverter
    public static Category integerToCategory(Integer value) {
      return (value != null) ? Category.values()[value] : null;
    }
  }
}
