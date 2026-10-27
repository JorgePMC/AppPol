package com.example.apppol.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppol.data.UserDbHelper
import com.example.apppol.ui.model.InfoMaterialComplementario
import com.example.apppol.ui.navegation.ScreenIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    data class InfoConsulta(
        val id: Int,
        val alumno: String,
        val asignatura: String,
        val pregunta: String,
        var respuesta: String = "",
        var respondido: Boolean = false
    )

    private val PnavigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = PnavigationEvents.asSharedFlow()

    fun navigateTo(route: ScreenIn) {
        viewModelScope.launch {
            PnavigationEvents.emit(NavigationEvent.NavigateTo(route))
        }
    }

    private val PesDocente = MutableStateFlow(false)
    val esDocente: StateFlow<Boolean> = PesDocente.asStateFlow()

    private val PloginExitoso = MutableStateFlow(false)
    val loginExitoso: StateFlow<Boolean> = PloginExitoso.asStateFlow()

    private val PnombreUsuario = MutableStateFlow("Cabo Perez")
    val nombreUsuario: StateFlow<String> = PnombreUsuario.asStateFlow()

    fun iniciarSesion(context: Context, nombre: String, correo: String, clave: String) {
        val correoLimpio = correo.trim().lowercase()
        val claveLimpia = clave.trim()

        // 1. NUEVO CORREO OFICIAL DEL DOCENTE
        if (correoLimpio == "pe.re@docentecarabineros.cl" && claveLimpia == "profe123") {
            PesDocente.value = true
            PloginExitoso.value = true
            PnombreUsuario.value = "Capitán Pedro Reyes"
            return
        }

        // 2. Verificación en SQLite para Alumnos
        val dbHelper = UserDbHelper(context)
        if (dbHelper.validarLogin(correoLimpio, claveLimpia)) {
            PesDocente.value = false
            PloginExitoso.value = true
            PnombreUsuario.value = if (nombre.isNotBlank()) nombre.trim() else "Cabo Perez"
        } else {
            PesDocente.value = false
            PloginExitoso.value = false
        }
    }

    fun cerrarSesion() {
        PloginExitoso.value = false
        PesDocente.value = false
        PnombreUsuario.value = "Cabo Perez"
    }

    private val PlistaMateriales = MutableStateFlow(
        listOf(
            InfoMaterialComplementario("El arte de la guerra", "Libro para entender comportamientos en situaciones tácticas.", "Estrategia Policial", "Sección 001D"),
            InfoMaterialComplementario("Defensa personal para principiantes", "Guía teórica de procedimientos de contención.", "Procedimientos Policiales II", "Sección 002D"),
            InfoMaterialComplementario("Manual de apoyo psicológico", "Herramientas para situaciones de alto estrés.", "Derechos Humanos", "Sección 001D")
        )
    )
    val listaMateriales: StateFlow<List<InfoMaterialComplementario>> = PlistaMateriales.asStateFlow()

    fun agregarMaterial(titulo: String, descripcion: String, ramo: String, seccion: String) {
        if (titulo.isNotBlank() && descripcion.isNotBlank()) {
            val listaActual = PlistaMateriales.value.toMutableList()
            listaActual.add(
                0,
                InfoMaterialComplementario(
                    titulo = titulo,
                    descripcion = descripcion,
                    ramo = if (ramo.isNotBlank()) ramo else "General",
                    seccion = if (seccion.isNotBlank()) seccion else "Sección 001D"
                )
            )
            PlistaMateriales.value = listaActual
        }
    }

    fun depurado(index: Int, depurado: Boolean) {
        val listaActualizada = PlistaMateriales.value.toMutableList()
        val material = listaActualizada[index]
        listaActualizada[index] = material.copy(estaDepurado = depurado)
        PlistaMateriales.value = listaActualizada
    }

    private val PlistaConsultas = MutableStateFlow(
        listOf(
            InfoConsulta(1, "Cabo 2do. J. Pérez", "Admin y Gestión en Seg. Pública", "¿Cuándo se entrega la bitácora de la Unidad 2?", "Estimado, la entrega vence este viernes a las 23:59 hrs.", true),
            InfoConsulta(2, "Cabo 1ro. M. González", "Procedimientos Policiales II", "Tengo una duda con respecto al protocolo de detención en la pregunta 3 del caso práctico.", "", false),
            InfoConsulta(3, "Carabinero F. Tapia", "Derecho Penal y Procesal Policial", "¿Cuáles son los plazos máximos para remitir el parte a fiscalía en procedimiento flagrante?", "", false),
            InfoConsulta(4, "Cabo 2do. C. Silva", "Derechos Humanos Aplicados", "Profesor, ¿el informe sobre uso progresivo de la fuerza requiere formato institucional o libre?", "Debe ser redactado en el formato oficial de minuta institucional.", true),
            InfoConsulta(5, "Carabinero A. Muñoz", "Procedimientos Policiales II", "Estimado Capitán, ¿se realizará control práctico presencial la próxima semana?", "", false)
        )
    )
    val listaConsultas: StateFlow<List<InfoConsulta>> = PlistaConsultas.asStateFlow()

    fun agregarConsulta(asignatura: String, pregunta: String) {
        val listaActual = PlistaConsultas.value.toMutableList()
        val nuevaConsulta = InfoConsulta(listaActual.size + 1, PnombreUsuario.value, asignatura, pregunta, "", false)
        listaActual.add(0, nuevaConsulta)
        PlistaConsultas.value = listaActual
    }

    fun responderConsulta(id: Int, respuesta: String) {
        val listaActual = PlistaConsultas.value.toMutableList()
        val index = listaActual.indexOfFirst { it.id == id }
        if (index != -1 && respuesta.isNotBlank()) {
            val consulta = listaActual[index]
            listaActual[index] = consulta.copy(respuesta = respuesta, respondido = true)
            PlistaConsultas.value = listaActual
        }
    }
}