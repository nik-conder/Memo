package com.app.memo.domain.repository

import com.app.memo.data.enities.Tag
import kotlinx.coroutines.flow.Flow

interface TagsRepository {
    suspend fun addTag(text: String): Long

    suspend fun getAllTags(): Flow<List<Tag>>

    suspend fun deleteTag(id: Int): Int
}