package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
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
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaIn(
    viewModel: MainViewModel,
    onNavegarProgreso: () -> Unit = {},
    onNavegarAsignaturas: () -> Unit = {},
    onNavegarConsultas: () -> Unit = {}
) {
    val nombreUsuario by viewModel.nombreUsuario.collectAsState()
    val context = LocalContext.current
    val verdePrincipal = Color(0xFF1B6717)

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(viewModel = viewModel)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ESPACIO DE SEGURIDAD PARA BAJAR EL CONTENIDO
            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header de Bienvenida
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
                        Column {
                            Text(
                                text = "Bienvenido/a,",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Text(
                                text = nombreUsuario,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Plataforma de Formación Continua",
                                color = Color(0xFFC8E6C9),
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Anuncio Importante
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "COMUNICADO INSTITUCIONAL",
                            fontWeight = FontWeight.Bold,
                            color = verdePrincipal,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Se recuerda a todos los funcionarios completar los módulos de Procedimientos Policiales II antes de la evaluación del 15 de Noviembre.",
                            fontSize = 13.sp
                        )
                    }
                }

                Text(
                    text = "Accesos Rápidos",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Botones de Accesos Rápidos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BotonAccesoRapido(
                        titulo = "Ver Progreso",
                        icono = Icons.Default.CheckCircle,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarProgreso
                    )
                    BotonAccesoRapido(
                        titulo = "Asignaturas",
                        icono = Icons.Default.Book,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarAsignaturas
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    BotonAccesoRapido(
                        titulo = "Consultas",
                        icono = Icons.Default.Email,
                        modifier = Modifier.weight(1f),
                        onClick = onNavegarConsultas
                    )
                    BotonAccesoRapido(
                        titulo = "Evaluaciones",
                        icono = Icons.Default.Star,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            Toast.makeText(context, "Para una futura actualización", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BotonAccesoRapido(
    titulo: String,
    icono: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icono, contentDescription = null, tint = Color(0xFF1B6717))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = titulo, fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }
}