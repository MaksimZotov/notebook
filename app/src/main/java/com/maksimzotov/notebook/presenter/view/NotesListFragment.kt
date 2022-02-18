package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentNotesListBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NotesListViewModel

class NotesListFragment: BaseFragment<NotesListViewModel, FragmentNotesListBinding>(
    NotesListViewModel::class.java,
    FragmentNotesListBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}