package com.example.apppol.ui.model

data class InfoMaterialComplementario(
    val titulo: String,
    val descripcion: String,
    val ramo: String = "Procedimientos Policiales II",
    val seccion: String = "Sección 001D",
    val estaDepurado: Boolean = false
)