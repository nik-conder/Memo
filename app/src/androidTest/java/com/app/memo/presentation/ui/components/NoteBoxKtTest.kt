package com.app.memo.presentation.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.app.memo.data.enities.Note
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

@HiltAndroidTest
internal class NoteBoxKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @BeforeEach
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `note_box`() {
        composeTestRule.setContent {
            NoteBox(note = Note(id = 1, title = "Заголовок", text = "Текст"))
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("NoteBox").assertExists()
        composeTestRule.onNodeWithTag("NoteBox").assertIsDisplayed()
    }

    @Test
    fun empty_note() {
        composeTestRule.setContent {
            NoteBox(note = Note(id = 1, title = null, text = null))
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("NoteBoxTitle").assertDoesNotExist()
        composeTestRule.onNodeWithTag("NoteBoxText").assertDoesNotExist()
    }

    @Test
    fun empty_text_note() {
        composeTestRule.setContent {
            NoteBox(note = Note(id = 1,  title = "Заголовок", text = null))
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("NoteBoxTitle").assertIsDisplayed().assertExists()
        composeTestRule.onNodeWithTag("NoteBoxText").assertDoesNotExist()

    }
    /*
    todo: Сделать, чтобы был либо заголовок либо текст
    todo: Сейчас создать заметку хотя бы без title null нельзя
     */
    @Test
    fun empty_title_note() {
        composeTestRule.setContent {
            NoteBox(note = Note(id = 1, text = "Текст", title = null))
        }
        composeTestRule.onNodeWithTag("NoteBoxTitle").assertDoesNotExist()
        composeTestRule.onNodeWithTag("NoteBoxText").assertIsDisplayed()
    }
}