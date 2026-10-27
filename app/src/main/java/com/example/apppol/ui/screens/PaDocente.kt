package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.R
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaDocente(
    viewModel: MainViewModel,
    onNavegarConsultas: () -> Unit,
    onNavegarMaterial: () -> Unit,
    onNavegarAsignaturas: () -> Unit,
    onNavegarPerfil: () -> Unit
) {
    val nombreUsuario by viewModel.nombreUsuario.collectAsState()
    val context = LocalContext.current
    val verdePrincipal = Color(0xFF1B6717)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding() // Evita que toque el notch y la barra de batería
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Espacio de resguardo para despejar la barra de estado superior
            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header del Docente
                Card(
                    colors = CardDefaults.cardColors(containerColor = verdePrincipal),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_carabineros),
                            contentDescription = "Logo",
                            modifier = Modifier.size(60.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Panel Administrativo",
                                color = Color(0xFFC8E6C9),
                                fontSize = 12.sp
                            )
                            Text(
                                text = nombreUsuario,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Academia de Ciencias Policiales",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }

                        // Botón directo al perfil
                        IconButton(onClick = onNavegarPerfil) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Perfil",
                                tint = Color.White
                            )
                        }
                    }
                }

                Text(
                    text = "Herramientas del Docente",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Fila 1: Responder Consultas y Gestión de Material
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BotonOpcionDocente(
                        titulo = "Responder\nConsultas",
                        icono = Icons.Default.Email,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarConsultas
                    )

                    BotonOpcionDocente(
                        titulo = "Subir y Depurar\nMaterial",
                        icono = Icons.Default.PlaylistAddCheck,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarMaterial
                    )
                }

                // Fila 2: Mis Asignaturas y Crear Evaluación
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BotonOpcionDocente(
                        titulo = "Asignaturas\na Cargo",
                        icono = Icons.Default.Book,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarAsignaturas
                    )

                    BotonOpcionDocente(
                        titulo = "Crear Nueva\nEvaluación",
                        icono = Icons.Default.Edit,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            Toast.makeText(context, "Para una futura actualización", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                // Publicar Comunicado Rápido
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Publicar Aviso a los Alumnos", fontWeight = FontWeight.Bold)
                        Text("Envíe una notificación a las secciones a su cargo.", fontSize = 12.sp, color = Color.Gray)

                        Button(
                            onClick = {
                                Toast.makeText(context, "Comunicado enviado a todas las secciones", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Emitir Aviso")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BotonOpcionDocente(
    titulo: String,
    icono: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.height(95.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icono, contentDescription = null, tint = Color(0xFF1B6717), modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = titulo, fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}