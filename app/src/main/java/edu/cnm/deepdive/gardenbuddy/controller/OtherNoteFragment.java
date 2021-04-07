package edu.cnm.deepdive.gardenbuddy.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.cnm.deepdive.gardenbuddy.R;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentOtherNoteBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import edu.cnm.deepdive.gardenbuddy.viewmodel.MainViewModel;
import edu.cnm.deepdive.gardenbuddy.viewmodel.NotesViewModel;
import org.jetbrains.annotations.NotNull;


public class OtherNoteFragment extends DialogFragment implements TextWatcher {

  private NotesViewModel notesViewModel;
  private FragmentOtherNoteBinding binding;
  private AlertDialog alertDialog;
  private MainViewModel mainViewModel;
  private Long noteId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = FragmentOtherNoteBinding.inflate(LayoutInflater.from(getContext()));
    alertDialog = new AlertDialog.Builder(getContext())
        .setTitle("New Other note")
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel,(dlg, which) -> {})
        .setPositiveButton(android.R.string.ok,(dlg, which) -> saveNote())
        .create();
    alertDialog.setOnShowListener((dlg) -> {
      binding.otherNote.addTextChangedListener(this);
      checkSubmitConditions();
    });
    return alertDialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  private void checkSubmitConditions() {
    Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    positive.setEnabled(!binding.otherNote.getText().toString().trim().isEmpty());
  }

  private void saveNote() {
    Note note = new Note();
    String otherNote = binding.otherNote.getText().toString().trim();
    note.setNote(otherNote);
    note.setPlantId(noteId);
    note.setCategory(Category.OTHER);
    mainViewModel.saveNote(note);
  }
}