package com.app.memo.data.repository

import android.content.Context
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val tagDAO: TagDAO
) : TagsRepository {

    override suspend fun addTag(tag: Tag) {
        return tagDAO.insertTag(tag)
    }

    override suspend fun getAllTags(): Flow<List<Tag>> {
        return tagDAO.getAllTags()
    }

    override suspend fun deleteTag(id: Int): Int {
        return tagDAO.deleteTag(id = id)
    }


}