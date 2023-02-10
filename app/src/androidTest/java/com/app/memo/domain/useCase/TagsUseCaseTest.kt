package com.app.memo.domain.useCase

import androidx.compose.ui.graphics.Color
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import javax.inject.Inject

@DisplayName("A special test case")
@HiltAndroidTest
internal class TagsUseCaseTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var tagsUseCase: TagsUseCase

    private val colorList: List<Long> = listOf(
        0xFF0039D7, 0xFF0DD29B, 0xFF9A52C8, 0xFF009F7D, 0xFF625b71, 0xFF6B1E74,
        0xFF5A2154, 0xFFECACC6, 0xFFC4F357, 0xFF459600, 0xFFABD415, 0xFF4564A2,
    )


    @Before
    fun init() {
        hiltRule.inject()
    }


    @Test
    fun addTag() {
        runBlocking {
            val result = tagsUseCase.addTag(Tag(text = "testTag"))
            println(result)
            assertNotNull(result)
        }
    }

    @Test
    fun getTag() {

        runBlocking {
            withTimeoutOrNull(100) {
                val addTag = tagsUseCase.getAllTags().lastOrNull()
                println("addTag $addTag")
                assertEquals(null, addTag)
                val result = tagsUseCase.getTag(id = 0)
            }
        }
    }
}