package com.maksimzotov.notebook.presenter.view

import android.os.Bundle
import android.view.View
import com.maksimzotov.notebook.databinding.FragmentNotesListBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.adapters.ItemsAboutAdapter
import com.maksimzotov.notebook.presenter.adapters.NotesAdapter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.parcelable.mapToParcelable
import com.maksimzotov.notebook.presenter.viewmodel.NotesListViewModel

class NotesListFragment: BaseFragment<NotesListViewModel, FragmentNotesListBinding>(
    NotesListViewModel::class.java,
    FragmentNotesListBinding::inflate
), OnItemClickListener {

    private lateinit var adapter: NotesAdapter

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter(emptyList(), this)
        binding.notes.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val note = adapter.notes[position]
        val action = NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
            note.mapToParcelable()
        )
        navController.navigate(action)
    }
}