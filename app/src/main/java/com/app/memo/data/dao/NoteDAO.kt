package com.app.memo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.memo.data.enities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY id DESC LIMIT :limit OFFSET :offset")
    fun getAllNotes(limit: Int, offset: Int): List<Note>

    @Query("SELECT * FROM Note ORDER BY id DESC LIMIT 1")
    fun getLastNote(): Int

}