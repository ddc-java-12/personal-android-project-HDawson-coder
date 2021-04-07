package edu.cnm.deepdive.gardenbuddy;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.gardenbuddy.model.entity.Plant;
import edu.cnm.deepdive.gardenbuddy.service.GardenBuddyDatabase;
import edu.cnm.deepdive.gardenbuddy.service.GoogleSignInService;
import io.reactivex.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class GardenBuddyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    Stetho.initializeWithDefaults(this);
    GardenBuddyDatabase.setContext(this);
    GardenBuddyDatabase.getInstance()
        .getPlantDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
