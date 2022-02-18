package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel

class NoteDetailsFragment: BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}