package edu.cnm.deepdive.gardenbuddy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotesViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public NotesViewModel() {
    mText = new MutableLiveData<>();
  }

  public LiveData<String> getText() {
    return mText;
  }
}