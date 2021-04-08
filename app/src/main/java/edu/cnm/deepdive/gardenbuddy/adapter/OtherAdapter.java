package edu.cnm.deepdive.gardenbuddy.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.gardenbuddy.adapter.OtherAdapter.Holder;
import edu.cnm.deepdive.gardenbuddy.databinding.ItemOtherNoteBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class OtherAdapter extends RecyclerView.Adapter<Holder> {

  private final LayoutInflater inflater;
  private final Context context;
  private final List<Note> notes;

  public OtherAdapter(Context context, List<Note> notes) {
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
    ItemOtherNoteBinding binding = ItemOtherNoteBinding.inflate(inflater, parent, false);
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

    private final ItemOtherNoteBinding otherBinding;
    private Note note;

    public Holder(ItemOtherNoteBinding binding) {
      super(binding.getRoot());
      this.otherBinding = binding;
      binding.getRoot();
    }

    private void Bind(Note note) {
      Category category = note.getCategory();
      String other = note.getNote();
      otherBinding.category.setText(category.toString());
      otherBinding.note.setText(other);
      otherBinding.getRoot();
    }
  }

}
