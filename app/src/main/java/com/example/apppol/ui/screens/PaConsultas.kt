package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaConsultas(viewModel: MainViewModel) {
    val esDocente by viewModel.esDocente.collectAsState()
    val listaConsultas by viewModel.listaConsultas.collectAsState()
    val context = LocalContext.current

    val verdeEncabezado = Color(0xFF1B6717)
    val verdeRespondido = Color(0xFFD4E7D0)
    val beigePendiente = Color(0xFFFFF3CD)

    var asignaturaText by remember { mutableStateOf("") }
    var mensajeText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            if (!esDocente) {
                BarraNavegacionInferior(viewModel = viewModel)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(verdeEncabezado)
                    .padding(vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (esDocente) "Panel Docente - Gestión de Consultas" else "Atención de Consultas",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // FORMULARIO PARA ALUMNOS
                if (!esDocente) {
                    Text("Realizar nueva consulta:", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = asignaturaText,
                        onValueChange = { asignaturaText = it },
                        label = { Text("Asignatura / Unidad") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = mensajeText,
                        onValueChange = { mensajeText = it },
                        label = { Text("Escriba su mensaje...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = {
                            if (asignaturaText.isNotBlank() && mensajeText.isNotBlank()) {
                                viewModel.agregarConsulta(asignaturaText, mensajeText)
                                asignaturaText = ""
                                mensajeText = ""
                                Toast.makeText(context, "Consulta enviada al docente", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enviar Consulta")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(
                    text = if (esDocente) "Consultas recibidas de alumnos:" else "Mis consultas:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listaConsultas) { consulta ->
                        var respuestaInput by remember { mutableStateOf("") }

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (consulta.respondido) verdeRespondido else beigePendiente
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (consulta.respondido) "RESPONDIDO" else "PENDIENTE DE RESPUESTA",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (consulta.respondido) Color(0xFF2E7D32) else Color(0xFF856404)
                                )
                                Text(text = "Alumno: ${consulta.alumno}", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                Text(text = "Asignatura: ${consulta.asignatura}", fontSize = 13.sp)
                                Text(text = "\"${consulta.pregunta}\"", fontSize = 13.sp, modifier = Modifier.padding(vertical = 4.dp))

                                if (consulta.respondido) {
                                    Text(
                                        text = "Respuesta: ${consulta.respuesta}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF1B5E20)
                                    )
                                } else if (esDocente) {
                                    // EL DOCENTE PUEDE RESPONDER AQUÍ
                                    Spacer(modifier = Modifier.height(8.dp))
                                    OutlinedTextField(
                                        value = respuestaInput,
                                        onValueChange = { respuestaInput = it },
                                        label = { Text("Escribir respuesta...") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Button(
                                        onClick = {
                                            if (respuestaInput.isNotBlank()) {
                                                viewModel.responderConsulta(consulta.id, respuestaInput)
                                                Toast.makeText(context, "Respuesta enviada", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        modifier = Modifier.align(Alignment.End)
                                    ) {
                                        Text("Responder")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}