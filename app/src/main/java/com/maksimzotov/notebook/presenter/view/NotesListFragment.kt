package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentNotesListBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.adapters.NotesAdapter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.viewmodel.NotesListViewModel

class NotesListFragment: FragmentWithoutParamsForVM<NotesListViewModel, FragmentNotesListBinding>(
    NotesListViewModel::class.java,
    FragmentNotesListBinding::inflate
), OnItemClickListener {

    private lateinit var adapter: NotesAdapter

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        adapter = NotesAdapter(emptyList(), this)
        with(binding) {
            notes.adapter = adapter
            add.setOnClickListener {
                val action =
                    NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
                        NoteDetailsFragment.DEFAULT_NOTE_ID
                    )
                navController.navigate(action)
            }
        }
    }

    override fun onItemClick(position: Int) {
        val note = adapter.notes[position]
        val action = NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
            note._id
        )
        navController.navigate(action)
    }
}