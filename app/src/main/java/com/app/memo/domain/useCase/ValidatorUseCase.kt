package com.app.memo.domain.useCase

import com.app.memo.data.Validator
import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag
import javax.inject.Inject

class ValidatorUseCase @Inject constructor() {

    class NoteValidator(note: Note) {
        val validator = Validator.NoteValidator(note = note)
    }

    class TagValidator(tag: Tag) {
        val validator = Validator::class
    }
}