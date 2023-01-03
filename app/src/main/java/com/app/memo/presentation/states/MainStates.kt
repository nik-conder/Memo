package com.app.memo.presentation.states

import com.app.memo.presentation.ui.navigation.Screen

data class MainStates(
    val startDestination: String = Screen.HomeScreen.route,
)