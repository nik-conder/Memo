package com.app.memo.data

import com.app.memo.ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TEXT
import com.app.memo.ConfigurationApp.Limits.NOTE_NUMBER_CHARACTERS_TITLE
import com.app.memo.ConfigurationApp.Limits.TAG_NUMBER_CHARACTERS_TEXT
import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag

interface Validator {

    interface ErrorOptions {

        enum class Tag : ErrorOptions {
            SUCCESS, LIMIT_TAG, LIMIT_TITLE_CHARS, INVALID_TITLE, INVALID_COLOR, EMPTY_TITLE
        }

        enum class Note : ErrorOptions {
            SUCCESS, EMPTY_TITLE_OR_TEXT, INVALID_TITLE, INVALID_TEXT, LIMIT_TITLE_CHARS, LIMIT_TEXT_CHARS, LIMIT_NOTES
        }

        enum class User : ErrorOptions {
            SUCCESS
        }
    }

    enum class SectionName {
        TAG, NOTE, USER
    }

    private val sectionNameList: Map<SectionName, Int>
        get() = mapOf(SectionName.TAG to 10, SectionName.NOTE to 20, SectionName.USER to 30)


    data class Message(
        val code: Int,
        val isValid: Boolean
    )

    private fun replace(string: String): String {
        val regex = """^[\s|\ㅤ]*|[\s|\ㅤ]*${'$'}""".toRegex()
        return regex.replace(string, "")
    }

    private fun formatCode(sectionName: SectionName, code: ErrorOptions): Int {

        if (sectionName == SectionName.TAG) {
            val sectionCode = sectionNameList[SectionName.TAG]
            return when (code) {
                ErrorOptions.Tag.SUCCESS -> "${sectionCode}1".toInt()
                ErrorOptions.Tag.INVALID_TITLE -> "${sectionCode}2".toInt()
                ErrorOptions.Tag.EMPTY_TITLE -> "${sectionCode}3".toInt()
                ErrorOptions.Tag.LIMIT_TITLE_CHARS -> "${sectionCode}4".toInt()
                ErrorOptions.Tag.LIMIT_TAG -> "${sectionCode}5".toInt()
                else -> 0
            }
        } else if (sectionName == SectionName.NOTE) {
            val sectionCode = sectionNameList[SectionName.NOTE]
            return when (code) {
                ErrorOptions.Note.SUCCESS -> "${sectionCode}1".toInt()
                ErrorOptions.Note.EMPTY_TITLE_OR_TEXT -> "${sectionCode}2".toInt()
                ErrorOptions.Note.INVALID_TITLE -> "${sectionCode}3".toInt()
                ErrorOptions.Note.INVALID_TEXT -> "${sectionCode}4".toInt()
                ErrorOptions.Note.LIMIT_TITLE_CHARS -> "${sectionCode}5".toInt()
                ErrorOptions.Note.LIMIT_TEXT_CHARS -> "${sectionCode}6".toInt()
                ErrorOptions.Note.LIMIT_NOTES -> "${sectionCode}7".toInt()
                else -> 0
            }
        } else if (sectionName == SectionName.USER) {
            val sectionCode = sectionNameList[SectionName.USER]
            return when (code) {
                ErrorOptions.User.SUCCESS -> "${sectionCode}1".toInt()
                else -> 0
            }

        } else {
            return 0
        }
    }

    class TagValidator(private val tag: Tag) : Validator {

        private val sectionName = SectionName.TAG

        fun isValid(): Message {
            if (tag.text != null) return isValidText(text = super.replace(tag.text))
            //if (it.color != null) return Message(code = 2, isValid = true)
            return Message(
                code = super.formatCode(
                    sectionName,
                    code = ErrorOptions.Tag.EMPTY_TITLE
                ), isValid = false
            )
        }

        private fun isValidText(text: String): Message {
            return if (text.isNotEmpty()) {
                if (text.length <= TAG_NUMBER_CHARACTERS_TEXT) {
                    Message(
                        code = super.formatCode(sectionName, ErrorOptions.Tag.SUCCESS),
                        isValid = true
                    )
                } else {
                    Message(
                        code = super.formatCode(
                            sectionName,
                            ErrorOptions.Tag.LIMIT_TITLE_CHARS
                        ), isValid = false
                    )
                }
            } else {
                Message(
                    code = super.formatCode(sectionName, ErrorOptions.Tag.INVALID_TITLE),
                    isValid = false
                )
            }
        }
    }

    class NoteValidator(private val note: Note) : Validator {

        private var result = Message(code = -1, isValid = false)
        private val sectionName = SectionName.NOTE

        fun isValid(): Message {

            if (note.title != null || note.text != null) {
                if (note.title != null) result = isValidTitle(text = super.replace(note.title))
                if (note.text != null) result = isValidText(text = super.replace(note.text))
            } else {
                result = Message(
                    code = super.formatCode(
                        sectionName,
                        ErrorOptions.Note.EMPTY_TITLE_OR_TEXT
                    ), isValid = false
                )
            }
            return result
        }

        private fun isValidTitle(text: String): Message {
            return if (text.isNotEmpty()) {
                if (text.length <= NOTE_NUMBER_CHARACTERS_TITLE) {
                    Message(
                        code = super.formatCode(sectionName, ErrorOptions.Note.SUCCESS),
                        isValid = true
                    )
                } else {
                    Message(
                        code = super.formatCode(
                            sectionName,
                            ErrorOptions.Note.LIMIT_TITLE_CHARS
                        ), isValid = false
                    )
                }
            } else {
                Message(
                    code = super.formatCode(sectionName, ErrorOptions.Note.INVALID_TITLE),
                    isValid = false
                )
            }
        }

        private fun isValidText(text: String): Message {
            return if (text.isNotEmpty()) {
                if (text.length <= NOTE_NUMBER_CHARACTERS_TEXT) {
                    Message(
                        code = super.formatCode(sectionName, ErrorOptions.Note.SUCCESS),
                        isValid = true
                    )
                } else {
                    Message(
                        code = super.formatCode(
                            sectionName,
                            ErrorOptions.Note.LIMIT_TEXT_CHARS
                        ), isValid = false
                    )
                }
            } else {
                Message(
                    code = super.formatCode(sectionName, ErrorOptions.Note.INVALID_TEXT),
                    isValid = false
                )
            }
        }
    }
}