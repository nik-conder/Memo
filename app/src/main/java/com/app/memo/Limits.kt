package com.app.memo

sealed interface ConfigurationApp {
    object Limits {
        const val TAGS_ADD: Int = 16
        const val TAG_NUMBER_CHARACTERS: Int = 16
        const val NOTE_NUMBER_CHARACTERS_TITLE: Int = 32
        const val NOTE_NUMBER_CHARACTERS_TEXT: Int = 1024
        const val NOTE_NUMBER_TAGS: Int = 8
    }

    interface Global {

    }
}