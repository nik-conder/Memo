package com.app.memo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.memo.data.enities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)
    @Query("SELECT * FROM Note WHERE id <= :lastID ORDER BY id DESC LIMIT :limit")
    fun getAllNotes(lastID: Int, limit: Int): List<Note>

    @Query("SELECT * FROM Note ORDER BY id DESC LIMIT 1")
    fun getLastNote(): Int

    @Delete
    fun deleteNote(note: Note)

}