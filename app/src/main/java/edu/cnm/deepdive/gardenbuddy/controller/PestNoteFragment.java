package edu.cnm.deepdive.gardenbuddy.controller;


import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.gardenbuddy.databinding.FragmentPestNoteBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import edu.cnm.deepdive.gardenbuddy.viewmodel.NotesViewModel;

/**
 * PestNoteFragment creates the Dialog to insert a new note into the database and allows the new
 * notes to be saved to the Plant Repository based on the category and plants selected.
 */
public class PestNoteFragment extends DialogFragment implements TextWatcher {

  private NotesViewModel notesViewModel;
  private FragmentPestNoteBinding binding;
  private AlertDialog alertDialog;
  private long plantId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      PestNoteFragmentArgs args = PestNoteFragmentArgs.fromBundle(getArguments());
      plantId = args.getPlantId();
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = FragmentPestNoteBinding.inflate(LayoutInflater.from(getContext()));
    alertDialog = new AlertDialog.Builder(getContext())
        .setTitle("New Pest Note")
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> saveNote())
        .create();
    alertDialog.setOnShowListener((dlg) -> {
      binding.pestNote.addTextChangedListener(this);
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
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    notesViewModel = new ViewModelProvider(getActivity()).get(NotesViewModel.class);
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
    positive.setEnabled(!binding.pestNote.getText().toString().trim().isEmpty());
  }

  private void saveNote() {
    Note note = new Note();
    String pestNote = binding.pestNote.getText().toString().trim();
    note.setNote(pestNote);
    note.setPlantId(plantId);
    note.setCategory(Category.PEST);
    notesViewModel.saveNote(note);
  }
}