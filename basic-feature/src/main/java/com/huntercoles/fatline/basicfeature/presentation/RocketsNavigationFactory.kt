package com.huntercoles.fatline.basicfeature.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.huntercoles.fatline.basicfeature.presentation.composable.RocketsRoute
import com.huntercoles.fatline.core.navigation.NavigationDestination.Rockets
import com.huntercoles.fatline.core.navigation.NavigationFactory
import javax.inject.Inject

class RocketsNavigationFactory @Inject constructor() : NavigationFactory {

    override fun create(builder: NavGraphBuilder) {
        builder.composable<Rockets> {
            RocketsRoute()
        }
    }
}
