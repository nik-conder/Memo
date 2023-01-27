package com.app.memo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.dao.UserDAO
import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag
import com.app.memo.data.enities.User

@Database(
    entities = [Tag::class, Note::class, User::class],
    version = 1, /* TODO: !!!!! */
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tagsDAO(): TagDAO
    abstract fun noteDAO(): NoteDAO
    abstract fun userDAO(): UserDAO
}