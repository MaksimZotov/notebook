package com.maksimzotov.notebook.presenter.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.R
import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel
import javax.inject.Inject

class NoteDetailsFragment: BaseFragment
<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {

    // ViewModel Initialization START **************************************
    private val args by navArgs<NoteDetailsFragmentArgs>()
    private val noteId by lazy { args.noteId }

    @Inject
    lateinit var factory: NoteDetailsViewModel.Factory.AssistedFactoryForVM
    override val viewModel by viewModels<NoteDetailsViewModel> {
        factory.create(noteId)
    }
    // **************************************** ViewModel Initialization END

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            save.setOnClickListener {
                val time =
                    if (deadline.text == getString(R.string.time))
                        null
                    else
                        deadline.text.toString()

                viewModel.saveNote(title.text.toString(), text.text.toString(), time)
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.note.observe { note ->
            with(binding) {
                title.setText(note?.title)
                text.setText(note?.text)
                if (note is NoteWithDeadline) {
                    deadline.text = note.deadline.toString()
                }
            }
        }
    }
}