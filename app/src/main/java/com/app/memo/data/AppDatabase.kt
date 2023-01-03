package com.app.memo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.dao.UserDAO
import com.app.memo.data.enities.Note
import com.app.memo.data.enities.Tag

@Database(
    entities = [Tag::class, Note::class],
    version = 1, /* TODO: !!!!! */
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tagsDAO(): TagDAO
    abstract fun noteDAO(): NoteDAO
    abstract fun userDAO(): UserDAO
}

//val MIGRATE_3_4  = object : Migration(3,4) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//
//        database.execSQL("ALTER TABLE Settings ADD saveResultEnabled INTEGER")
//        database.execSQL("ALTER TABLE Settings ADD hideActionBarEnabled BOOLEAN")
//        database.execSQL("ALTER TABLE Settings ADD savingOnlyRecordEnabled BOOLEAN")
//        database.execSQL("ALTER TABLE Settings ADD adsContinueGameEnabled BOOLEAN")
//        database.execSQL("ALTER TABLE Settings RENAME hideStatusBarEnabled to hideActionBarEnabled")
//    }
//}
//
//val MIGRATE_4_5 = object : Migration(3,4) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//
//        //database.execSQL("DROP TABLE User")
//    }
//}