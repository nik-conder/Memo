package com.app.memo.di.modules

import com.app.memo.presentation.viewModels.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [])
class PresentationModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

}