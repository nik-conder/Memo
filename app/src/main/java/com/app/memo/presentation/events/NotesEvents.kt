package com.app.memo.presentation.events

import com.app.memo.data.enities.Note

sealed class NotesEvents() {
    class AddNote(val note: Note): NotesEvents()
    object RefreshListNotes: NotesEvents()
    class DeleteNote(): NotesEvents()
    class EditNote(): NotesEvents()
    object ShowCreateNoteBox: NotesEvents()
}