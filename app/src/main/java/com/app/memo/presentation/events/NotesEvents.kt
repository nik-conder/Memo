package com.app.memo.presentation.events

import com.app.memo.data.enities.Note

sealed class NotesEvents() {
    class AddNote(val note: Note): NotesEvents()
    class DeleteNote(val note: Note): NotesEvents()
    class EditNote(): NotesEvents()
    object ShowCreateNoteBox: NotesEvents()
    object OpenAlertDialogDeleteNote: NotesEvents()
    class UpdateInitKeyPaging(val key: Int): NotesEvents()

    object GenerateNotes: NotesEvents()
}