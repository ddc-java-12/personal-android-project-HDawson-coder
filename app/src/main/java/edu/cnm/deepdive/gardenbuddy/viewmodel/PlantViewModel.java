package edu.cnm.deepdive.gardenbuddy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Currently a placeholder that displays the text saying it is a plant fragment when the page is loaded.
 */
public class PlantViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  /**
   * Displays the text stating "This is a plant fragment" when the user is on the Plant Page.
   */
  public PlantViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is Plant fragment");
  }

  /**
   * Returns the text set in the text field to display on the page.
   * @return
   */
  public LiveData<String> getText() {
    return mText;
  }
}