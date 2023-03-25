package com.app.memo.di.modules

import android.content.Context
import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.dao.TagDAO
import com.app.memo.data.paging.NotesPagingSource
import com.app.memo.data.repository.NotesRepositoryImpl
import com.app.memo.data.repository.TagsRepositoryImpl
import com.app.memo.domain.repository.NotesRepository
import com.app.memo.domain.repository.TagsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [RoomModule::class])
class DataModule @Inject constructor() {

    @Singleton
    @Provides
    fun providerTagsRepository(
        @ApplicationContext context: Context,
        tagDAO: TagDAO
    ): TagsRepository {
        return TagsRepositoryImpl(context, tagDAO)
    }

    @Singleton
    @Provides
    fun providerNotesRepository(
        @ApplicationContext context: Context,
        noteDAO: NoteDAO,
    ): NotesRepository {
        return NotesRepositoryImpl(
            context,
            noteDAO
        )
    }
}