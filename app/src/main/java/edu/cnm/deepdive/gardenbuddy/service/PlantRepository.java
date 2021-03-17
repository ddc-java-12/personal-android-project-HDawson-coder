//package edu.cnm.deepdive.gardenbuddy.service;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.res.Resources;
//import androidx.preference.PreferenceManager;
//import edu.cnm.deepdive.roulette.R;
//
//public class PlantRepository {
//
//  private final Context context;
//  private final SharedPreferences preferences;
//  private final Resources resources;
//
//  public PlantRepository(Context context) {
//    this.context = context;
//    preferences = PreferenceManager.getDefaultSharedPreferences(context);
//    resources = context.getResources();
//  }
//
//  public int getMinimumWager() {
//    return preferences.getInt(context.getString(R.string.minimum_wager_key),
//        resources.getInteger(R.integer.minimum_wager_default));
//  }
//
//  public int getStartingPot() {
//    return preferences.getInt(resources.getString(R.string.starting_pot_key),
//        resources.getInteger(R.integer.starting_pot_default));
//  }
//
//  public boolean isWagerRiding() {
//    return preferences.getBoolean(resources.getString(R.string.wager_riding_key),
//        resources.getBoolean(R.bool.wager_riding_default));
//  }
//}
