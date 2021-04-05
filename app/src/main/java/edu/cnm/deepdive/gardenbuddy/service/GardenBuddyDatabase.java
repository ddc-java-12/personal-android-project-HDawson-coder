package edu.cnm.deepdive.gardenbuddy.service;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.model.entity.History;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.model.dao.HistoryDao;
import edu.cnm.deepdive.gardenbuddy.model.dao.NoteDao;
import edu.cnm.deepdive.gardenbuddy.model.dao.PlantDao;
import edu.cnm.deepdive.gardenbuddy.service.GardenBuddyDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;

@Database(
    entities = {History.class, Plant.class, Note.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(value = {Converters.class, Note.Category.class})
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

  public abstract NoteDao getNoteDao();

  public abstract PlantDao getPlantDao();

  private static class InstanceHolder {

    private static final GardenBuddyDatabase INSTANCE =
        Room.databaseBuilder(context, GardenBuddyDatabase.class, DB_NAME)
            .addCallback(new CallBack())
            .build();

  }

  private static class CallBack extends RoomDatabase.Callback{

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      insertPlants();
    }

    private void insertPlants() {
      try (
          InputStream inputStream = context.getResources().openRawResource(R.raw.plants);
          Reader reader = new InputStreamReader(inputStream);
          CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
              .withIgnoreEmptyLines()
          .withIgnoreSurroundingSpaces());
          ) {
        List<Plant> plants = new LinkedList<>();
        for (CSVRecord record : parser) {
          Plant plant = new Plant();
          plant.setCommonName(record.get(0));
          plant.setScientificName(record.get(1));
          plant.setMinTemp(Integer.parseInt(record.get(2)));
          plant.setMaxTemp(Integer.parseInt(record.get(3)));
          plant.setDaysToMaturity(Integer.parseInt(record.get(4)));
          plant.setSpacingInInches(Integer.parseInt(record.get(5)));
          plants.add(plant);
        }
        GardenBuddyDatabase.getInstance().getPlantDao().insert(plants)
            .subscribeOn(Schedulers.io())
            .subscribe(
                (ids) -> {
                },
                (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
            );
      } catch(IOException e) {
        Log.e(getClass().getSimpleName(), e.getMessage(), e);
        throw new RuntimeException(e);
      }
    }
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
