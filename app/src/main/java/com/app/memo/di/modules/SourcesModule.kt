package com.app.memo.di.modules

import com.app.memo.data.dao.NoteDAO
import com.app.memo.data.paging.NotesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [RoomModule::class])
class SourcesModule @Inject constructor() {
    @Singleton
    @Provides
    fun provideNotesPagingSource(
        noteDAO: NoteDAO
    ): NotesPagingSource {
        return NotesPagingSource(noteDAO)
    }
}