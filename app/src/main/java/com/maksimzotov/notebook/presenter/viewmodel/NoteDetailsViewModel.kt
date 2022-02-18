package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.usecases.notes.AddNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.UpdateNoteUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    private val note: Note,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
): BaseViewModel() {

    fun addNote() = viewModelScope.launch(Dispatchers.IO) {
        addNoteUseCase.addNote(note)
    }

    fun updateNote() = viewModelScope.launch(Dispatchers.IO) {
        updateNoteUseCase.updateNote(note)
    }
}