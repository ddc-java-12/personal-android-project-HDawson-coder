package edu.cnm.deepdive.gardenbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.gardenbuddy.adapter.PestAdapter.Holder;
import edu.cnm.deepdive.gardenbuddy.databinding.ItemPestNoteBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import java.util.List;

public class PestAdapter extends RecyclerView.Adapter<Holder> {

  private final LayoutInflater inflater;
  private final Context context;
  private final List<Note> notes;

  public PestAdapter(Context context, List<Note> notes) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.notes = notes;
  }

  public List<Note> getNotes() {
    return notes;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemPestNoteBinding binding = ItemPestNoteBinding.inflate(inflater, parent, false);
    return new Holder(binding);

  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.Bind(notes.get(position));
  }

  @Override
  public int getItemCount() {
    return notes.size();
  }

  static class Holder extends RecyclerView.ViewHolder {

    private final ItemPestNoteBinding pestBinding;
    private Note note;

    public Holder(ItemPestNoteBinding binding) {
      super(binding.getRoot());
      this.pestBinding = binding;
      binding.getRoot();
    }

    private void Bind(Note note) {
      Category category = note.getCategory();
      String pest = note.getNote();
      pestBinding.category.setText(category.toString());
      pestBinding.note.setText(pest);
      pestBinding.getRoot();
    }
  }
}
