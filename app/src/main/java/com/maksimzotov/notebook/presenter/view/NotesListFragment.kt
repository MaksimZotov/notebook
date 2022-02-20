package com.maksimzotov.notebook.presenter.view

import androidx.fragment.app.setFragmentResultListener
import com.maksimzotov.notebook.databinding.FragmentNotesListBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.adapters.NotesAdapter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.util.OnItemLongClickListener
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.view.dialogs.RemoveDialogFragment
import com.maksimzotov.notebook.presenter.viewmodel.NotesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesListFragment: FragmentWithoutParamsForVM<NotesListViewModel, FragmentNotesListBinding>(
    NotesListViewModel::class.java,
    FragmentNotesListBinding::inflate
), OnItemClickListener, OnItemLongClickListener {

    private lateinit var adapter: NotesAdapter

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        adapter = NotesAdapter(emptyList(), this, this)
        with(binding) {
            notes.adapter = adapter
            add.setOnClickListener {
                viewModel.navigateToNoteDetailsToAddNewNote()
            }
        }

        setFragmentResultListener(RemoveDialogFragment.REMOVE_KEY) { _, bundle ->
            val remove = bundle.getBoolean(
                RemoveDialogFragment.EXTRA_REMOVE,
                false
            )
            if (remove) {
                val noteId = bundle.getInt(RemoveDialogFragment.EXTRA_NOTE_ID)
                viewModel.removeNote(noteId)
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.notes.observe { notes ->
            adapter.setData(notes)
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.navigateToNoteDetailsToEditNoteWithId(adapter.notes[position]._id)
    }

    override fun onItemLongClick(position: Int) {
        viewModel.navigateToRemoveDialog(adapter.notes[position]._id)
    }
}