package com.app.memo.data.repository

import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag
import com.app.memo.domain.repository.NotesRepository
import com.app.memo.domain.repository.TagsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import javax.inject.Inject

@HiltAndroidTest
internal class NotesRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Inject
    lateinit var notesRepository: NotesRepository

    private val testNote = Note(id = -1, title = "Test note title", text = "Test note text")

    @Test
    fun addNote() {
        runBlocking {

            val nullNote = notesRepository.addNote(note = Note(id = 0, title = null, text = null))
            assertNotNull(nullNote)

            val noteEmpty = notesRepository.addNote(note = Note(id = -1, title = null, text = null))
            val noteOnlyTitle = notesRepository.addNote(Note(id = -1, title = "Note only with title"))
            val noteOnlyText = notesRepository.addNote(Note(id = -1, title = "Note only with title"))

            assertEquals(false, noteEmpty)
            assertEquals(true, noteOnlyTitle)
            assertEquals(true, noteOnlyText)
        }
    }

    @Test
    fun getAllNotes() {
        runBlocking {
            withTimeoutOrNull(250) {
                val result = notesRepository.getAllNotes()
                // todo
            }
        }
    }

    @Test
    fun updateAllNotes() {
    }

    @Test
    fun getContext() {
    }
}