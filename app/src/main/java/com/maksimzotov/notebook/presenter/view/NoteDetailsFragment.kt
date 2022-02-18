package com.maksimzotov.notebook.presenter.view

import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.databinding.FragmentNoteDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.domain.entities.note.NoteWithAlarm
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel

class NoteDetailsFragment: BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding>(
    NoteDetailsViewModel::class.java,
    FragmentNoteDetailsBinding::inflate
) {
    private val args by navArgs<NoteDetailsFragmentArgs>()
    private val note by lazy { args.note.mapToNote() }

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            title.setText(note.title)
            text.setText(note.text)
            if (note is NoteWithAlarm) {
                timeToAlarm.text = (note as NoteWithAlarm).timeToNotify.toString()
            }
        }
    }
}