package com.app.memo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.memo.data.enities.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(tag: Tag)

    @Query("DELETE FROM Tag")
    fun deleteAllTags(): Int

    @Query("SELECT * FROM Tag")
    fun getAllTags(): Flow<List<Tag>>

    @Query("INSERT INTO Tag(text) VALUES (:text) ")
    fun addTag(text: String): Long

    @Query("DELETE FROM Tag WHERE uid = (:id)")
    fun deleteTag(id: Int): Int


}