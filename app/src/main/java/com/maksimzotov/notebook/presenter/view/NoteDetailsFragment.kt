package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.R
import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel

class NoteDetailsFragment: FragmentWithoutParamsForVM
<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {

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