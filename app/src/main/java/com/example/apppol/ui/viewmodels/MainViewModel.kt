package com.example.apppol.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppol.ui.navegation.ScreenIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import android.content.Context

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.apppol.data.UserDbHelper
import com.example.apppol.ui.model.InfoMaterialComplementario

class MainViewModel : ViewModel() {

    private val PesDocente = MutableStateFlow(false)
    val esDocente: StateFlow<Boolean> = PesDocente.asStateFlow()

    private val PloginExitoso = MutableStateFlow(false)
    val loginExitoso: StateFlow<Boolean> = PloginExitoso.asStateFlow()

    fun iniciarSesion(context: Context, nombre: String, correo: String, clave: String) {
        if (nombre == "Pedro Reyes" && correo == "docente@duocuc.cl" && clave == "profe123") {
            PesDocente.value = true
            PloginExitoso.value = true
            return
        }

        val dbHelper = UserDbHelper(context)
        if (dbHelper.validarLogin(correo, clave)) {
            PesDocente.value = false
            PloginExitoso.value = true
        } else {
            PloginExitoso.value = false
        }
    }
    private val PnavigationEvents = MutableSharedFlow<NavigationEvent>()

    val navigationEvents: SharedFlow<NavigationEvent> = PnavigationEvents.asSharedFlow()

    fun navigateTo(screen: ScreenIn) {
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.NavigateTo(route = screen))
        }
    }

    /* no se va a usar, pero por ahora nomas, sas.
    fun navigateBack() {
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }
    fun navigateUp(){
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.navigateUp)
        }
    */
    private val PlistaMateriales = MutableStateFlow(
        listOf(
            InfoMaterialComplementario("El arte de la guerra", "Libro para entender comportamientos o situaciones bélicas."),
            InfoMaterialComplementario("Defensa personal para principiantes", "Libro para la teoría de cómo defenderse en diferentes situaciones."),
            InfoMaterialComplementario("Libro de auto ayuda", "Para situaciones donde se vean abrumados por situaciones delicadas.")
        )
    )
    val listaMateriales: StateFlow<List<InfoMaterialComplementario>> = PlistaMateriales.asStateFlow()

    fun depurado(index: Int, depurado: Boolean) {
        val listaActualizada = PlistaMateriales.value.toMutableList()
        val material = listaActualizada[index]
        listaActualizada[index] = material.copy(estaDepurado = depurado)
        PlistaMateriales.value = listaActualizada
    }
}