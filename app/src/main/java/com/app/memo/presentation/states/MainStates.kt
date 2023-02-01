package com.app.memo.presentation.states

import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag
import com.app.memo.presentation.ui.navigation.Screen

data class MainStates(
    val startDestination: String = Screen.HomeScreen.route,
    val tagsList: List<Tag>? = null,
    val notesList: List<Note>?= null,
    val openAlertDialogAddTag: Boolean = false,
    val showCreateNoteBox: Boolean = false,
    val addTagTextState: String = ""
)