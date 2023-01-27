package com.app.memo.domain.useCase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.enities.Tag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import javax.inject.Inject

@DisplayName("A special test case")
@HiltAndroidTest
internal class TagsUseCaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var tagDAO: TagDAO


    @Before
    fun init() {
        hiltRule.inject()
    }


    suspend fun createTag( text: String, color: Long) {
        return tagDAO.insertTag(Tag(text = text, color = color))
    }


    @Test
    fun addTag() {
        runBlocking {
            val result = createTag(text = "tag", color = 0xFFF)
            for (i in 0..5) {
                createTag(text = "tag$i", color = 0xFFF)
            }
            assertNotNull(result)
            println("result: $result")
        }
    } 

}