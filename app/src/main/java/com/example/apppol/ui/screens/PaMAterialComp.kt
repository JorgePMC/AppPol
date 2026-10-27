package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
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
fun PantallaMaCo(viewModel: MainViewModel) {
    val esDocente by viewModel.esDocente.collectAsState()
    val listaMateriales by viewModel.listaMateriales.collectAsState()
    val context = LocalContext.current

    var nuevoTitulo by remember { mutableStateOf("") }
    var nuevoRamo by remember { mutableStateOf("") }
    var nuevaSeccion by remember { mutableStateOf("") }
    var nuevaDescripcion by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            if (!esDocente) {
                BarraNavegacionInferior(viewModel = viewModel)
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (esDocente) "Panel Docente - Material de Apoyo" else "Material de Apoyo",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // FORMULARIO DOCENTE
                if (esDocente) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Publicar nuevo material", fontWeight = FontWeight.Bold)

                            OutlinedTextField(
                                value = nuevoTitulo,
                                onValueChange = { nuevoTitulo = it },
                                label = { Text("Título del archivo/guía") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedTextField(
                                    value = nuevoRamo,
                                    onValueChange = { nuevoRamo = it },
                                    label = { Text("Asignatura/Ramo") },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true
                                )

                                OutlinedTextField(
                                    value = nuevaSeccion,
                                    onValueChange = { nuevaSeccion = it },
                                    label = { Text("Sección") },
                                    modifier = Modifier.weight(1f),
                                    singleLine = true
                                )
                            }

                            OutlinedTextField(
                                value = nuevaDescripcion,
                                onValueChange = { nuevaDescripcion = it },
                                label = { Text("Descripción corta") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Button(
                                onClick = {
                                    if (nuevoTitulo.isNotBlank() && nuevaDescripcion.isNotBlank()) {
                                        viewModel.agregarMaterial(
                                            titulo = nuevoTitulo,
                                            descripcion = nuevaDescripcion,
                                            ramo = nuevoRamo,
                                            seccion = nuevaSeccion
                                        )
                                        nuevoTitulo = ""
                                        nuevoRamo = ""
                                        nuevaSeccion = ""
                                        nuevaDescripcion = ""
                                        Toast.makeText(context, "¡Material publicado con éxito!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Complete al menos Título y Descripción", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Publicar Material")
                            }
                        }
                    }
                }

                // LISTA DE MATERIALES CON CHECKBOX INTERACTIVO Y NOTIFICACIÓN
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(listaMateriales) { index, material ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = material.titulo,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "${material.ramo} • ${material.seccion}",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                    }

                                    if (esDocente) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.padding(start = 8.dp)
                                        ) {
                                            Text(
                                                text = "Depurado",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Checkbox(
                                                checked = material.estaDepurado,
                                                onCheckedChange = { checkeado ->
                                                    viewModel.depurado(index, checkeado)
                                                    val aviso = if (checkeado) "Material marcado como Depurado" else "Material desmarcado"
                                                    Toast.makeText(context, aviso, Toast.LENGTH_SHORT).show()
                                                }
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = material.descripcion,
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                OutlinedButton(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Descargando '${material.titulo}.pdf' con éxito...",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    modifier = Modifier.align(Alignment.End)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Download,
                                        contentDescription = "Descargar",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Descargar PDF", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}