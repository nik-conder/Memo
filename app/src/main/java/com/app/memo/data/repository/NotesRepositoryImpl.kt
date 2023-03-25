package com.app.memo.data.repository

import android.content.Context
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.enities.Note
import com.app.memo.data.paging.NotesPagingMediator
import com.app.memo.data.paging.NotesPagingSource
import com.app.memo.domain.repository.NotesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val noteDAO: NoteDAO
) : NotesRepository {

    override suspend fun addNote(note: Note) = noteDAO.insertNote(note)
    override fun getAllNotesSource(): NotesPagingSource {
        return NotesPagingSource(noteDAO)
    }

    override fun getAllNotesMediator(): NotesPagingMediator {
        return NotesPagingMediator(noteDAO)
    }

    override fun test(): String {
        return "Hello world :)"
    }

    override fun deleteNote(note: Note) {
        return noteDAO.deleteNote(note)
    }

}