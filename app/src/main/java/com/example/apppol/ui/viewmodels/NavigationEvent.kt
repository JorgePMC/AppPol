package com.example.apppol.ui.viewmodels

import com.example.apppol.ui.navegation.ScreenIn

sealed class NavigationEvent {
    data class NavigateTo(
        val route: ScreenIn,
        val popUpToRoute: ScreenIn? = null,
        /*val inclusive: Boolean = false,*/
        val singleTop: Boolean = true
    ) : NavigationEvent()
    /*
    object PopBackStack : NavigationEvent()
    se ocupara mas adelante, por mientras estara desactivado
    object NavigateUp : NavigationEvent()
    */
}