package com.app.memo.data

import com.app.memo.R

/*
 Классификация кодов ошибок:

номер раздела + номер ошибки, например:

не валидный заголовок в Note 201

## Tag (10)

1. успех
2. неправильное название
3. пустое название
4. лимит символов в названии
5. лимит тегов
6. лимит добавления новых тегов

## Note (20)

1. успех
2. пустой заголовок или пустое содержание
3. не валидный заголовок
4. не валидное содержание
5. лимит символов в заголовке
6. лимит символов в содержании
7. лимит добавления новых заметок

## User (30)

### Name

1. успех
2. поле ввода пустое
3. не валидное имя
4. лимит символов
 */
interface SystemMessages {
    class Message(private val code: Int) {
        fun getMessage(): Int {
            return when (code) {
                101 -> R.string.unknown_error
                102 -> R.string.unknown_error
                103 -> R.string.unknown_error
                104 -> R.string.unknown_error
                105 -> R.string.unknown_error
                106 -> R.string.unknown_error

                201 -> R.string.unknown_error
                202 -> R.string.unknown_error
                203 -> R.string.unknown_error
                204 -> R.string.unknown_error

                301 -> R.string.unknown_error
                302 -> R.string.unknown_error
                303 -> R.string.unknown_error
                304 -> R.string.unknown_error
                305 -> R.string.unknown_error

                else -> R.string.unknown_error
            }
        }
    }
}