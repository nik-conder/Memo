package com.app.memo.di.modules

import android.content.Context
import androidx.room.Room
import com.app.memo.data.AppDatabase
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.dao.TagDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RoomModule @Inject constructor() {

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-db")
            .fallbackToDestructiveMigration()
            //.addMigrations(MIGRATE_3_4)
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providerTagDAO(appDatabase: AppDatabase): TagDAO {
        return appDatabase.tagsDAO()
    }

    @Singleton
    @Provides
    fun providerNoteDAO(appDatabase: AppDatabase): NoteDAO {
        return appDatabase.noteDAO()
    }


}