package edu.cnm.deepdive.gardenbuddy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public PlantViewModel() {

  }

  public LiveData<String> getText() {
    return mText;
  }
}