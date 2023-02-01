package com.app.memo.domain.useCase

import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagsUseCase @Inject constructor(
    private val tagsRepository: TagsRepository
) {

    fun loadingTags() {

    }

    suspend fun addTag(tag: Tag) {
        return tagsRepository.addTag(tag)
    }

    suspend fun getAllTags(): Flow<List<Tag>> {
        return tagsRepository.getAllTags()
    }

    suspend fun deleteTag(id: Int): Int {
        return tagsRepository.deleteTag(id = id)
    }
}