package com.app.memo.data.repository

import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import javax.inject.Inject

@HiltAndroidTest
internal class TagsRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Inject
    lateinit var tagsRepository: TagsRepository

    private val testTag = Tag(id = -1, text = "Tag")

    @Test
    fun addTag() {
        runBlocking {

            val nulLTag = tagsRepository.addTag(Tag(text = null))
            assertEquals(false, nulLTag)

            val tagWithText = tagsRepository.addTag(Tag(text = "Test"))
            assertEquals(true, tagWithText)

            val tagWithColor = tagsRepository.addTag(Tag(text = null, color = 0xFFFFC300))
            assertEquals(false, tagWithColor)

            val tagWithTextAndColor = tagsRepository.addTag(Tag(text = "Test", color = 0xFFFFC300))
            assertEquals(true, tagWithTextAndColor)

        }
    }

    @Test
    fun getAllTags() {

        var listTag: List<Tag> = listOf()

        runBlocking {
            withTimeoutOrNull(250) {
                tagsRepository.getAllTags().collect() { listTag = it }
            }
        }

        assertNotNull(listTag)
    }

    @Test
    fun deleteTag() {
        runBlocking {
            val result = tagsRepository.deleteTag(testTag.id)
            assertNotNull(result)
        }
    }

    @Test
    fun getTag() {
        runBlocking {
            val addTag = tagsRepository.addTag(Tag(id = -1, text = "Tag"))
            val negativeParam = tagsRepository.getTag(id = testTag.id)
            assertNotNull(negativeParam)
        }
    }
}
