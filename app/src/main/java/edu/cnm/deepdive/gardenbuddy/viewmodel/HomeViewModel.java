package edu.cnm.deepdive.gardenbuddy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Currently just a placeholder. The homeViewModel displays the text stating it is the home
 * fragment.
 */
public class HomeViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  /**
   * Displays the text "This is a home fragment" when the home screen is loaded.
   */
  public HomeViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("Welcome to Garden Buddy!");
  }

  /**
   * Gets the text set in the field and returns for the application to display.
   * @return
   */
  public LiveData<String> getText() {
    return mText;
  }
}