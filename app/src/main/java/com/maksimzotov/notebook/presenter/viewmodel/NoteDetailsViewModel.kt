package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.domain.usecases.notes.AddNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.GetNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.UpdateNoteUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): BaseViewModel() {

    companion object {
        const val DEFAULT_NOTE_ID = -1

        /**
         * Since in landscape orientation we need to show the selected note on two fragments,
         * sending the id of the selected note from NotesList to NoteDetails via Safe Args
         * will not work. Therefore, I had to use this crutch.
         */
        var CURRENT_NOTE_ID = DEFAULT_NOTE_ID
    }

    val note = getNoteUseCase.getNote(CURRENT_NOTE_ID)
        .stateIn(null)

    fun saveNote(title: String, text: String, deadline: String?) = viewModelScope.launch {
        if (CURRENT_NOTE_ID == DEFAULT_NOTE_ID)
            if (deadline != null)
                addNoteUseCase.addNote(NoteWithDeadline(
                    title = title,
                    text = text,
                    time = Date(0),
                    deadline = Date(deadline.length.toLong())
                ))
            else
                addNoteUseCase.addNote(Note(
                    title = title,
                    text = text,
                    time = Date(0)
                ))
        else
            if (deadline != null)
                updateNoteUseCase.updateNote(NoteWithDeadline(
                    _id = CURRENT_NOTE_ID,
                    title = title,
                    text = text,
                    time = Date(0),
                    deadline = Date(deadline.length.toLong())
                ))
            else
                addNoteUseCase.addNote(Note(
                    _id = CURRENT_NOTE_ID,
                    title = title,
                    text = text,
                    time = Date(0)
                ))
    }
}