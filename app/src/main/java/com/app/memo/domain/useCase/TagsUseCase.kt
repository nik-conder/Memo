package com.app.memo.domain.useCase

import androidx.compose.ui.graphics.Color
import com.app.memo.data.Validator
import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagsUseCase @Inject constructor(
    private val tagsRepository: TagsRepository
) {

    private val colorList: List<Long> = listOf(
        0xFF0039D7, 0xFF0DD29B, 0xFF9A52C8, 0xFF009F7D, 0xFF625b71, 0xFF6B1E74,
        0xFF5A2154, 0xFFECACC6, 0xFFC4F357, 0xFF459600, 0xFFABD415, 0xFF4564A2,
    )

    fun getColor(color: Long): Color {
        return Color(color)
    }

    suspend fun getTag(id: Int): Tag {
        return tagsRepository.getTag(id = id)
    }

    suspend fun addTag(tag: Tag): Boolean {
        val isValid = Validator.TagValidator(tag).isValid()
        return if (isValid.isValid) {
            tagsRepository.addTag(tag)
        } else {
            false
        }
    }

    suspend fun getAllTags(): Flow<List<Tag>> {
        return tagsRepository.getAllTags()
    }

    suspend fun deleteTag(id: Int): Int {
        return tagsRepository.deleteTag(id = id)
    }
}