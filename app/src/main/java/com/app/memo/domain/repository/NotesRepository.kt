package com.app.memo.domain.repository

import com.app.memo.data.enities.Note
import com.app.memo.data.paging.NotesPagingSource

interface NotesRepository {
    suspend fun addNote(note: Note)
    fun getAllNotes(): NotesPagingSource
    fun updateAllNotes()
}