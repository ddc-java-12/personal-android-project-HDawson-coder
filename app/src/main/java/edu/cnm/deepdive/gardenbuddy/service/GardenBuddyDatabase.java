package edu.cnm.deepdive.gardenbuddy.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.HistoryDao;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.NoteDao;
import edu.cnm.deepdive.gardenbuddy.model.entity.dao.PlantDao;
import java.util.Date;

@Database(
    entities = {History.class, Plant.class, Note.class},
//    views = {ValueCount.class}, TODO figure out what this needs to be
    version = 1
)

//@TypeConverters(value = {Converters.class, Color.class}) TODO figure out what this needs to be

public abstract class GardenBuddyDatabase extends RoomDatabase {

  private static final String DB_NAME = "gardenbuddy_db";

  private static Application context;

  public static void setContext(Application context) {
    GardenBuddyDatabase.context = context;
  }

  public static GardenBuddyDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract HistoryDao getHistoryDao();

  public abstract NoteDao getWagerDao();

  public abstract PlantDao getPlantDao();

  private static class InstanceHolder {

    private static final GardenBuddyDatabase INSTANCE =
        Room.databaseBuilder(context, GardenBuddyDatabase.class, DB_NAME)
            .build();

  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}
