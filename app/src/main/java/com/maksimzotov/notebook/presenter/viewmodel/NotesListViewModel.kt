package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.usecases.notes.GetNotesUseCase
import com.maksimzotov.notebook.domain.usecases.notes.RemoveNoteUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import com.maksimzotov.notebook.presenter.view.NotesListFragmentDirections
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
            NoteDetailsViewModel.NOTE_TO_ADD_ID
        ))

    fun navigateToNoteDetailsToEditNoteWithId(id: Int) =
        navigate(NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(
            id
        ))
}