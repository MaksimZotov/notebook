package com.maksimzotov.notebook.presenter.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel
import javax.inject.Inject

class NoteDetailsFragment: BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {
    private val args by navArgs<NoteDetailsFragmentArgs>()
    private val noteId: Int by lazy { args.noteId }

    @Inject
    lateinit var factory: NoteDetailsViewModel.Factory.AssistedFactoryForVM
    override val viewModel by viewModels<NoteDetailsViewModel> {
        factory.create(noteId)
    }

    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}