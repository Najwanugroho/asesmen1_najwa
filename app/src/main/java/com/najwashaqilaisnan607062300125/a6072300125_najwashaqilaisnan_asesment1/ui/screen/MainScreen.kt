package com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.R
import com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.navigation.Screen
import com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.ui.theme._6072300125_NajwaShaqilaIsnaN_asesment1Theme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var translated by rememberSaveable { mutableStateOf(false) } // Menyimpan status translate

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = stringResource(R.string.gambar),
                            modifier = Modifier.size(40.dp).padding(end = 8.dp)
                        )
                        Text(text = if (translated) "Speed Calculator" else "Kalkulator Kecepatan")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = if (translated) "About App" else "Tentang Aplikasi",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding), translated) { translated = it }
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, translated: Boolean, onTranslateChange: (Boolean) -> Unit) {
    var jarak by rememberSaveable { mutableStateOf("") }
    var jarakError by rememberSaveable { mutableStateOf(false) }

    var waktu by rememberSaveable { mutableStateOf("") }
    var waktuError by rememberSaveable { mutableStateOf(false) }

    var kecepatan by rememberSaveable { mutableStateOf(0f) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (translated) "Enter distance (km) and time (hours)"
            else "Masukkan jarak (km) dan waktu (jam)",
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = jarak,
            onValueChange = { jarak = it },
            label = {
                Text(text = if (translated) "Distance" else "Jarak")
            },
            isError = jarakError,
            trailingIcon = { IconPicker(jarakError, "km") },
            supportingText = { ErrorHint(jarakError, translated) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = waktu,
            onValueChange = { waktu = it },
            label = {
                Text(text = if (translated) "Time" else "Waktu")
            },
            isError = waktuError,
            trailingIcon = { IconPicker(waktuError, "jam") },
            supportingText = { ErrorHint(waktuError, translated) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                jarakError = (jarak.isEmpty() || jarak == "0")
                waktuError = (waktu.isEmpty() || waktu == "0")
                if (jarakError || waktuError) return@Button

                val jarakNum = jarak.toFloatOrNull()
                val waktuNum = waktu.toFloatOrNull()
                if (jarakNum != null && waktuNum != null && waktuNum > 0) {
                    kecepatan = (jarakNum / waktuNum * 100).roundToInt() / 100f
                }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = if (translated) "Calculate Speed" else "Hitung Kecepatan")
        }

        if (kecepatan > 0f) {
            Text(
                text = if (translated) "Speed: $kecepatan km/h"
                else "Kecepatan: $kecepatan km/jam",
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    shareData(
                        context,
                        if (translated) "The calculated speed is $kecepatan km/h"
                        else "Kecepatan yang dihitung adalah $kecepatan km/jam"
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = if (translated) "Share" else "Bagikan")
            }
        }

        Button(
            onClick = { onTranslateChange(!translated) },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = if (translated) "Indonesian" else "English")
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean, translated: Boolean) {
    if (isError) {
        Text(text = if (translated) "Invalid input!" else "Input tidak valid!")
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GreetingPreview() {
    _6072300125_NajwaShaqilaIsnaN_asesment1Theme {
        MainScreen(rememberNavController())
    }
}