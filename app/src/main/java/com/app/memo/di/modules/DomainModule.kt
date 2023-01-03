package com.app.memo.di.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class DomainModule @Inject constructor() {
//    @Singleton
//    @Provides
//    fun providerColorsUseCase(): ColorsUseCase {
//        return ColorsUseCase()
//    }
//
//
//    @Singleton
//    @Provides
//    fun providerLoadSettingsUseCase(
//        settingsRepository: SettingsRepositoryImpl,
//        darkModeUseCase: DarkModeUseCase
//    ): LoadSettingsUseCase {
//        return LoadSettingsUseCase(settingsRepository, darkModeUseCase)
//    }

}