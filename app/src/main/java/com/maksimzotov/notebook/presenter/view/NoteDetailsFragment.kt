package com.maksimzotov.notebook.presenter.view

import android.app.DatePickerDialog
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
                viewModel.saveNote(
                    title.text.toString(),
                    text.text.toString(),
                    deadline.text.toString()
                )
            }

            deadline.setOnClickListener {
                val date = viewModel.getDeadlineDayMonthYearByText(deadline.text.toString())
                val day = date[0]
                val month = date[1]
                val year = date[2]

                DatePickerDialog(
                    requireActivity(),
                    { _, _year, _month, _day ->
                        deadline.text =
                            viewModel.getTextByYearMonthDay(_day, _month + 1, _year)
                    },
                    year,
                    month - 1,
                    day
                ).show()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.note.observe { note ->
            with(binding) {
                title.setText(note?.title)
                text.setText(note?.text)

                if (note is NoteWithDeadline)
                    deadline.text = viewModel.formatDate(note.deadline)
                else
                    deadline.text = getString(R.string.deadline)
            }
        }
    }
}