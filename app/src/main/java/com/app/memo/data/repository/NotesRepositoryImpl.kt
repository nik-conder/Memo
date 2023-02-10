package com.app.memo.data.repository

import android.content.Context
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.enities.Note
import com.app.memo.data.paging.NotesPagingSource
import com.app.memo.domain.repository.NotesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val noteDAO: NoteDAO,
    private val notesPagingSource: NotesPagingSource
) : NotesRepository {

    override suspend fun addNote(note: Note) = noteDAO.insertNote(note)

    override fun test(): String {
        return "Hello world :)"
    }

    override fun getAllNotes(): NotesPagingSource = notesPagingSource

    override fun updateAllNotes() =  notesPagingSource.invalidate()

}