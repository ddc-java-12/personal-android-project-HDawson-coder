package edu.cnm.deepdive.gardenbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.gardenbuddy.adapter.WeatherAdapter.Holder;
import edu.cnm.deepdive.gardenbuddy.databinding.ItemWeatherNoteBinding;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note;
import edu.cnm.deepdive.gardenbuddy.model.entity.Note.Category;
import java.util.List;

/**
 * WeatherAdapter class allows the recycler view of the user input notes to be displayed on the UI.
 */
public class WeatherAdapter extends RecyclerView.Adapter<Holder> {

  private final LayoutInflater inflater;
  private final Context context;
  private final List<Note> notes;

  /**
   * This sets the fields for context and notes to be used in the adapter.
   * @param context grabs the context of the class to be  used.
   * @param notes gives a list of notes.
   */
  public WeatherAdapter(Context context, List<Note> notes) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.notes = notes;
  }

  /**
   * Gets the list of Notes from {@link Note} to be used for new notes set.
   * @return
   */
  public List<Note> getNotes() {
    return notes;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemWeatherNoteBinding binding = ItemWeatherNoteBinding.inflate(inflater, parent, false);
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

  /**
   * The Holder class will hold the notes in the Weather Category fragment to be displayed and allows
   * the WeatherAdapter class to use them.
   */
  static class Holder extends RecyclerView.ViewHolder {

    private final ItemWeatherNoteBinding weatherBinding;
    private Note note;

    /**
     * This will bind the notes set as Weather notes to be bound to the display of the Item display
     * for other notes and then be set on the UI display when called upon.
     * @param binding binds the ItemWeatherAdapter layout to the display and notes.
     */
    public Holder(ItemWeatherNoteBinding binding) {
      super(binding.getRoot());
      this.weatherBinding = binding;
      binding.getRoot();
    }

    private void Bind(Note note) {
      Category category = note.getCategory();
      String pest = note.getNote();
      weatherBinding.category.setText(category.toString());
      weatherBinding.note.setText(pest);
      weatherBinding.getRoot();
    }
  }

}
