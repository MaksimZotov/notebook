package com.maksimzotov.notebook.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maksimzotov.notebook.R
import com.maksimzotov.notebook.domain.entities.note.Note
import com.maksimzotov.notebook.domain.entities.note.NoteWithDeadline
import com.maksimzotov.notebook.domain.usecases.notes.AddNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.GetNoteUseCase
import com.maksimzotov.notebook.domain.usecases.notes.UpdateNoteUseCase
import com.maksimzotov.notebook.presenter.main.util.DateConverter
import com.maksimzotov.notebook.presenter.main.util.ResourceProvider
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class NoteDetailsViewModel @Inject constructor(
    private val noteId: Int,
    private val resourceProvider: ResourceProvider,
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): BaseViewModel() {

    companion object {
        private const val ASSISTED_NOTE_ID = "ASSISTED_NOTE_ID"

        const val NOTE_TO_ADD_ID = -1
    }

    private val dateConverter = DateConverter()

    val note: StateFlow<Note?> = getNoteUseCase.getNote(noteId)
        .stateIn(null)

    fun saveNote(title: String, text: String, deadline: String) = viewModelScope.launch {
        if (title.isBlank()) {
            showShortToast(resourceProvider.getString(R.string.title_is_blank))
            return@launch
        }
        if (noteId == NOTE_TO_ADD_ID)
            if (deadline != resourceProvider.getString(R.string.deadline))
                addNoteUseCase.addNote(NoteWithDeadline(
                    title = title,
                    text = text,
                    time = Calendar.getInstance().time,
                    deadline = dateConverter.parse(deadline)
                ))
            else
                addNoteUseCase.addNote(Note(
                    title = title,
                    text = text,
                    time = Calendar.getInstance().time
                ))
        else
            if (deadline != resourceProvider.getString(R.string.deadline))
                updateNoteUseCase.updateNote(NoteWithDeadline(
                    _id = noteId,
                    title = title,
                    text = text,
                    time = Calendar.getInstance().time,
                    deadline = dateConverter.parse(deadline)
                ))
            else
                addNoteUseCase.addNote(Note(
                    _id = noteId,
                    title = title,
                    text = text,
                    time = Calendar.getInstance().time
                ))

        popBackStack()
    }

    fun formatDate(date: Date) = dateConverter.format(date)

    fun getDeadlineDayMonthYearByText(text: String): List<Int> =
        dateConverter.getDayMonthYearByText(
            if (text == resourceProvider.getString(R.string.deadline))
                dateConverter.format(Calendar.getInstance().time)
            else
                text
        )

    fun getTextByYearMonthDay(day: Int, month: Int, year: Int): String =
        dateConverter.getTextByYearMonthDay(day, month, year)

    class Factory @AssistedInject constructor(
        @Assisted(ASSISTED_NOTE_ID) private val noteId: Int,
        private val resourceProvider: ResourceProvider,
        private val getNoteUseCase: GetNoteUseCase,
        private val addNoteUseCase: AddNoteUseCase,
        private val updateNoteUseCase: UpdateNoteUseCase
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NoteDetailsViewModel(
                noteId,
                resourceProvider,
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