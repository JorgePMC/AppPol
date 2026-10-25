package com.example.apppol.ui.navegation

sealed class ScreenIn (val route: String) {
    data object Inicio : ScreenIn(route = "Pa-inicio")
    data object Materias : ScreenIn(route = "Pa-materia")
    data object Material : ScreenIn(route = "Pa-material-com")
    data object Perfil : ScreenIn(route = "Pa-perfil")
}