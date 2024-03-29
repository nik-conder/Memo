package com.app.memo.domain.repository

import com.app.memo.data.enities.Note
import com.app.memo.data.paging.NotesPagingMediator
import com.app.memo.data.paging.NotesPagingSource

interface NotesRepository {
    suspend fun addNote(note: Note)
    fun getAllNotesSource(): NotesPagingSource
    fun getAllNotesMediator(): NotesPagingMediator
    fun test(): String

    fun deleteNote(note: Note)
}