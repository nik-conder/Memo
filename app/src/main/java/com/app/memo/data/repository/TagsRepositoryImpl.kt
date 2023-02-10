package com.app.memo.data.repository

import android.content.Context
import com.app.memo.data.Validator
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

    override suspend fun addTag(tag: Tag): Boolean {
        val isValid = Validator.TagValidator(tag).isValid()

        return if (isValid.isValid) {
            val result = tagDAO.insertTag(tag)
            return result > 0
        } else {
            false
        }
    }

    override suspend fun getAllTags(): Flow<List<Tag>> {
        return tagDAO.getAllTags()
    }

    override suspend fun deleteTag(id: Int): Int {
        return tagDAO.deleteTag(id = id)
    }

    override suspend fun getTag(id: Int): Tag {
        return tagDAO.getTag(id = id)
    }


}