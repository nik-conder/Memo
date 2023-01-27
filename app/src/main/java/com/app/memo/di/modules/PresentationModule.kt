package com.app.memo.di.modules

import android.content.Context
import com.app.memo.domain.useCase.TagsUseCase
import com.app.memo.presentation.viewModels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [])
class PresentationModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideMainViewModel(@ApplicationContext context: Context,tagsUseCase: TagsUseCase): MainViewModel {
        return MainViewModel(context, tagsUseCase)
    }

}