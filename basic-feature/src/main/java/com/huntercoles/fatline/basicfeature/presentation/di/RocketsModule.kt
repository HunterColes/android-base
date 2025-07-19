package com.huntercoles.fatline.basicfeature.presentation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import com.huntercoles.fatline.basicfeature.presentation.RocketsNavigationFactory
import com.huntercoles.fatline.basicfeature.presentation.RocketsUiState
import com.huntercoles.fatline.core.navigation.NavigationFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object RocketsViewModelModule {

    @Provides
    fun provideInitialRocketsUiState(): RocketsUiState = RocketsUiState()
}

@Module
@InstallIn(SingletonComponent::class)
internal interface RocketsSingletonModule {

    @Singleton
    @Binds
    @IntoSet
    fun bindRocketsNavigationFactory(factory: RocketsNavigationFactory): NavigationFactory
}
