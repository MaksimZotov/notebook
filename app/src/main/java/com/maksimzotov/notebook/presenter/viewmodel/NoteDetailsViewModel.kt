package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.domain.usecases.notes.AddNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.GetNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.UpdateNoteUseCase
import com.maksimzotov.notebook.presenter.main.util.Constants.EMPTY_STRING
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.*

class NoteDetailsViewModel(
    private val noteId: Int,
    getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): BaseViewModel() {

    companion object {
        private const val ASSISTED_NOTE_ID = "noteId"

        const val DEFAULT_NOTE_ID = -1
    }

    val note = getNoteUseCase.getNote(noteId)
        .stateIn(Note(0, EMPTY_STRING, EMPTY_STRING, Date(0)))

    fun saveNote(title: String, text: String, deadline: String?) = viewModelScope.launch {
        if (noteId == DEFAULT_NOTE_ID)
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
                    _id = noteId,
                    title = title,
                    text = text,
                    time = Date(0),
                    deadline = Date(deadline.length.toLong())
                ))
            else
                addNoteUseCase.addNote(Note(
                    _id = noteId,
                    title = title,
                    text = text,
                    time = Date(0)
                ))
    }

    class Factory @AssistedInject constructor(
        @Assisted(ASSISTED_NOTE_ID) private val noteId: Int,
        private val getNoteUseCase: GetNoteUseCase,
        private val addNoteUseCase: AddNoteUseCase,
        private val updateNoteUseCase: UpdateNoteUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NoteDetailsViewModel(
                noteId,
                getNoteUseCase,
                addNoteUseCase,
                updateNoteUseCase
            ) as T
        }

        @AssistedFactory
        interface AssistedFactoryForVM {
            fun create(@Assisted(ASSISTED_NOTE_ID) noteId: Int): Factory
        }
    }
}