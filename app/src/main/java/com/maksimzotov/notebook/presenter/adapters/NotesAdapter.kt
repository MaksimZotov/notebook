package com.maksimzotov.notebook.presenter.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.notebook.databinding.ItemNoteBinding
import com.maksimzotov.notebook.databinding.ItemNoteWithDeadlineBinding
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.presenter.main.util.DateConverter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.util.OnItemLongClickListener

sealed class BaseNoteViewHolder(
    root: View,
    private val onItemClickListener: OnItemClickListener,
    private val onItemLongClickListener: OnItemLongClickListener,
) : RecyclerView.ViewHolder(root) {

    @SuppressLint("SimpleDateFormat")
    val dateFormat = DateConverter()

    init {
        with(root) {
            setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
            setOnLongClickListener {
                onItemLongClickListener.onItemLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
}

class NoteViewHolder(
    private val binding: ItemNoteBinding,
    onCityClickListener: OnItemClickListener,
    onItemLongClickListener: OnItemLongClickListener
) : BaseNoteViewHolder(binding.root, onCityClickListener, onItemLongClickListener) {

    fun bind(item: Note) {
        with(binding) {
            title.text = item.title
            text?.text = item.text
            time.text = dateFormat.format(item.time)
        }
    }
}

class NoteWithDeadlineViewHolder(
    private val binding: ItemNoteWithDeadlineBinding,
    onCityClickListener: OnItemClickListener,
    onItemLongClickListener: OnItemLongClickListener
) : BaseNoteViewHolder(binding.root, onCityClickListener, onItemLongClickListener) {

    fun bind(item: NoteWithDeadline) {
        with(binding) {
            title.text = item.title
            text?.text = item.text
            time.text = dateFormat.format(item.time)
            deadline.text = dateFormat.format(item.deadline)
        }
    }
}

class NotesAdapter(
    var notes: List<Note>,
    private val onItemClickListener: OnItemClickListener,
    private val onItemLongClickListener: OnItemLongClickListener
) : RecyclerView.Adapter<BaseNoteViewHolder>() {

    companion object {
        private const val NOTE_TYPE = 1
        private const val NOTE_WITH_DEADLINE_TYPE = 2
    }

    fun setData(newNotes: List<Note>) {
        val diffUtil = DiffUtilNotes(notes, newNotes)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        notes = newNotes
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseNoteViewHolder =
        if (viewType == NOTE_TYPE)
            NoteViewHolder(
                ItemNoteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemClickListener,
                onItemLongClickListener
            )
        else
            NoteWithDeadlineViewHolder(
                ItemNoteWithDeadlineBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemClickListener,
                onItemLongClickListener
            )

    override fun onBindViewHolder(holder: BaseNoteViewHolder, position: Int) {
        val note = notes[position]
        if (holder is NoteViewHolder)
            holder.bind(notes[position])
        else if (note is NoteWithDeadline && holder is NoteWithDeadlineViewHolder) {
            holder.bind(note)
        }
    }

    override fun getItemCount(): Int = notes.size

    override fun getItemViewType(position: Int): Int =
        if (notes[position] is NoteWithDeadline)
            NOTE_WITH_DEADLINE_TYPE
        else
            NOTE_TYPE
}

class DiffUtilNotes(
    private val oldList: List<Note>,
    private val newList: List<Note>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition]._id == newList[newItemPosition]._id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].time == newList[newItemPosition].time
}