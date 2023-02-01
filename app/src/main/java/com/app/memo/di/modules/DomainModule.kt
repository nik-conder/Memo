package com.app.memo.di.modules

import com.app.memo.domain.repository.NotesRepository
import com.app.memo.domain.repository.TagsRepository
import com.app.memo.domain.useCase.NotesUseCase
import com.app.memo.domain.useCase.TagsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule @Inject constructor() {
    @Singleton
    @Provides
     fun provideTagsUseCase(tagsRepository: TagsRepository): TagsUseCase {
         return TagsUseCase(tagsRepository)
     }

    @Singleton
    @Provides
    fun providerNotesUseCase(notesRepository: NotesRepository): NotesUseCase {
        return NotesUseCase(notesRepository)
    }
}