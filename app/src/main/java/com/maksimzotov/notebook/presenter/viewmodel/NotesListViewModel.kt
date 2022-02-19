package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.usecases.notes.GetNotesUseCase
import com.maksimzotov.notebook.domain.usecases.notes.RemoveNoteUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import com.maksimzotov.notebook.presenter.view.NotesListFragmentDirections
import com.maksimzotov.notebook.presenter.viewmodel.NoteDetailsViewModel.Companion.DEFAULT_NOTE_ID
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel @Inject constructor(
    getNotesUseCase: GetNotesUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase
): BaseViewModel() {

    val notes: StateFlow<List<Note>> = getNotesUseCase.getNotes().stateIn(emptyList())

    fun removeNote(note: Note) = viewModelScope.launch {
        removeNoteUseCase.removeNote(note)
    }

    fun navigateToNoteDetailsToAddNewNote() =
        navigate(NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
            DEFAULT_NOTE_ID
        ))

    fun navigateToNoteDetailsToEditNoteWithId(id: Int) =
        navigate(NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
            id
        ))
}