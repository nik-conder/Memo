package com.app.memo.data.repository

import android.content.Context
import com.app.memo.data.dao.NoteDAO
import com.app.memo.domain.repository.NotesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    val noteDAO: NoteDAO
) : NotesRepository {

}