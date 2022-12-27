package com.app.memo.di.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module(includes = [])
class AppModule @Inject constructor() {

//    @Singleton
//    @Provides
//    fun providerDataModule(): DataModule {
//        return DataModule()
//    }

}
