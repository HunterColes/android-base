package com.huntercoles.fatline.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    navController: NavHostController,
    factories: Set<NavigationFactory>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Portfolio,
        modifier = modifier,
    ) {
        factories.forEach {
            it.create(this)
        }
    }
}
