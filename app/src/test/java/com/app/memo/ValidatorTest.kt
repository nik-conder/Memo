package com.app.memo

import com.app.memo.data.Validator
import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Validator ")
class ValidatorTest {

    @Nested
    inner class Tag {

        @Test
        @DisplayName("Is empty")
        fun isEmpty() {
            val expectedIsValid = false
            val expectedCode = 103

            val result = Validator.TagValidator(Tag(id = 1, text = null)).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Not empty, has a title. No color.")
        fun notEmptyHasTitle() {
            val expectedIsValid = true
            val expectedCode = 101

            val result = Validator.TagValidator(Tag(id = 1, text = "Tag")).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Limit of chars in title")
        fun limitCharsTitle() {
            val expectedIsValid = false
            val expectedCode = 104

            val result = {
                // Generating a string where the limit will be exceeded
                var string = ""
                for (i in 0..ConfigurationApp.Limits.TAG_NUMBER_CHARACTERS_TEXT + 10) string += "a"
                Validator.TagValidator(Tag(id = 1, text = string)).isValid()
            }

            assertEquals(expectedIsValid, result.invoke().isValid)
            assertEquals(expectedCode, result.invoke().code)
        }

        @Test
        @DisplayName("Title has only text has only whitespace")
        fun titleWhitespace() {
            val expectedIsValid = false
            val expectedCode = 102

            val result = Validator.TagValidator(Tag(id = 1, text = " ")).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

    }

    @Nested
    inner class Note {

        @Test
        @DisplayName("Empty")
        fun noteEmpty() {
            val expectedIsValid = false
            val expectedCode = 202

            val result = Validator.NoteValidator(Note(id = 1, title = null, text = null)).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Not empty")
        fun noteNotEmpty() {
            //todo отличается от noteOnlyTitleAndText
            val expectedIsValid = true
            val expectedCode = 201

            val result = Validator.NoteValidator(Note(id = 1, title = "Title", text = "Text")).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Only title")
        fun noteOnlyTitle() {
            val expectedIsValid = true
            val expectedCode = 201

            val result =
                Validator.NoteValidator(Note(id = 1, title = "Title", text = null)).isValid()
            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Only text")
        fun noteOnlyText() {
            val expectedIsValid = true
            val expectedCode = 201

            val result =
                Validator.NoteValidator(Note(id = 1, title = null, text = "Text")).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Only title and text")
        fun noteOnlyTitleAndText() {
            val expectedIsValid = true
            val expectedCode = 201

            val result =
                Validator.NoteValidator(Note(id = 1, title = "Title", text = "Text")).isValid()

            assertEquals(expectedIsValid, result.isValid)
            assertEquals(expectedCode, result.code)
        }

        @Test
        @DisplayName("Limit of chars in title")
        fun noteLimitTitle() {
            val expectedIsValid = false
            val expectedCode = 205

            val result = {
                // Generating a string where the limit will be exceeded
                var string = ""
                for (i in 0..ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TITLE + 10) string += "a"
                Validator.NoteValidator(Note(id = 1, title = string)).isValid()
            }

            assertEquals(expectedIsValid, result.invoke().isValid)
            assertEquals(expectedCode, result.invoke().code)
        }

        @Test
        @DisplayName("Limit of chars in text")
        fun noteLimitText() {
            val expectedIsValid = false
            val expectedCode = 206

            val result = {
                // Generating a string where the limit will be exceeded
                var string = ""
                for (i in 0..ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TEXT + 10) string += "a"
                Validator.NoteValidator(Note(id = 1, text = string)).isValid()
            }
            assertEquals(expectedIsValid, result.invoke().isValid)
            assertEquals(expectedCode, result.invoke().code)
        }

    }
}