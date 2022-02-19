package com.maksimzotov.notebook.presenter.view

import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm
import com.maksimzotov.notebook.presenter.main.di.BaseViewModelFactory
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel

class NoteDetailsFragment: BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {
    private val args by navArgs<NoteDetailsFragmentArgs>()
    private val noteId: Int by lazy { args.noteId }



    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}