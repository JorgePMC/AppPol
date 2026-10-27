package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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

data class MateriaItem(
    val id: Int,
    val nombre: String,
    val docente: String,
    val secciones: String,
    val unidades: List<String>
)

@Composable
fun PantallaMa(viewModel: MainViewModel) {
    val esDocente by viewModel.esDocente.collectAsState()
    val context = LocalContext.current
    val verdeTarjeta = Color(0xFFE8F5E9)

    val listaMaterias = listOf(
        MateriaItem(1, "Derecho Penal y Procesal Policial", "Capitán R. Morales", "Sección 001D - 002D", listOf("Unidad 1: Marco Legal", "Unidad 2: Código Procesal")),
        MateriaItem(2, "Procedimientos Policiales II", "Capitán J. Martínez", "Sección 001D", listOf("Unidad 1: Control de Identidad", "Unidad 2: Detención")),
        MateriaItem(3, "Admin. y Gestión en Seguridad Pública", "Capitán P. Rodríguez", "Sección 003D", listOf("Unidad 1: Recurso Humano", "Unidad 2: Logística")),
        MateriaItem(4, "Derechos Humanos Aplicados", "Mayor A. Soto", "Sección 001D - 004D", listOf("Unidad 1: Uso Gradual de la Fuerza", "Unidad 2: Protocolos"))
    )

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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (esDocente) "Gestión de Asignaturas\n(Modo Docente)" else "Asignaturas del\nSemestre Activo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color(0xFF1B6717)
                )
            }

            Text(
                text = if (esDocente) "Seleccione una materia para administrar temario u opciones:" else "Presione una asignatura para ver el temario:",
                fontSize = 13.sp,
                color = Color.Gray
            )

            listaMaterias.forEach { materia ->
                var expandido by remember { mutableStateOf(false) }

                Card(
                    colors = CardDefaults.cardColors(containerColor = verdeTarjeta),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandido = !expandido }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = materia.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Docente: ${materia.docente} (${materia.secciones})", fontSize = 13.sp)

                        AnimatedVisibility(visible = expandido) {
                            Column(modifier = Modifier.padding(top = 10.dp)) {
                                Divider()
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = "Contenido / Unidades:", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                materia.unidades.forEach { unidad ->
                                    Text(text = "• $unidad", fontSize = 13.sp, modifier = Modifier.padding(start = 8.dp, top = 2.dp))
                                }

                                // HERRAMIENTAS EXCLUSIVAS PARA EL DOCENTE
                                if (esDocente) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        OutlinedButton(
                                            onClick = {
                                                Toast.makeText(context, "Para una futura actualización", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text("Editar Programa", fontSize = 11.sp)
                                        }

                                        OutlinedButton(
                                            onClick = {
                                                Toast.makeText(context, "Lista de alumnos exportada", Toast.LENGTH_SHORT).show()
                                            },
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text("Ver Alumnos", fontSize = 11.sp)
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
}