package com.app.memo.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.app.memo.data.AppDatabase
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val tagDAO: TagDAO
) : TagsRepository {

    override suspend fun addTag(text: String): Long {
        return tagDAO.addTag(text = text)
    }

    override suspend fun getAllTags(): Flow<List<Tag>> {
        return tagDAO.getAllTags()
    }

    override suspend fun deleteTag(id: Int): Int {
        return tagDAO.deleteTag(id = id)
    }


}