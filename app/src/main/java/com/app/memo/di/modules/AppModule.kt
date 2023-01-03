package com.app.memo.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DomainModule::class, DataModule::class, PresentationModule::class])
class AppModule @Inject constructor() {

    @Singleton
    @Provides
    fun providerDataModule(): DataModule {
        return DataModule()
    }

    @Singleton
    @Provides
    fun providerDomainModule(): DomainModule {
        return DomainModule()
    }

    @Singleton
    @Provides
    fun providerPresentationModule(): PresentationModule {
        return PresentationModule()
    }
}