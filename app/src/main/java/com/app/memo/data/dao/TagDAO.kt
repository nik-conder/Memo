package com.app.memo.data.dao

import androidx.room.*
import com.app.memo.data.enities.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface ConvertersTags {
    @TypeConverter
    fun fromTagToString(tagsList: List<Tag>): String {
        return Json.encodeToString(tagsList)
    }

    @TypeConverter
    fun fromTagToList(tagsString: String): List<Tag> {
        return Json.decodeFromString(tagsString)
    }
}
@Dao
interface TagDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(tag: Tag)

    @Query("DELETE FROM Tag")
    fun deleteAllTags(): Int

    @Query("SELECT * FROM Tag")
    fun getAllTags(): Flow<List<Tag>>

    @Query("DELETE FROM Tag WHERE id = (:id)")
    fun deleteTag(id: Int): Int


}