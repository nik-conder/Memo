package com.app.memo.domain.useCase

import com.app.memo.data.enities.Note
import com.app.memo.data.paging.NotesPagingMediator
import com.app.memo.data.paging.NotesPagingSource
import com.app.memo.domain.repository.NotesRepository
import javax.inject.Inject

class NotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun addNote(note: Note) {
        return notesRepository.addNote(note)
    }

    fun getAllNotesSource(): NotesPagingSource = notesRepository.getAllNotesSource()
    fun getAllNotesMediator(): NotesPagingMediator = notesRepository.getAllNotesMediator()

    fun deleteNote(note: Note) {
        notesRepository.deleteNote(note)
    }
}