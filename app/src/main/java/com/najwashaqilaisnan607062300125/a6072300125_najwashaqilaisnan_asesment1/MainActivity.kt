package com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.navigation.SetupNavGraph
import com.najwashaqilaisnan607062300125.a6072300125_najwashaqilaisnan_asesment1.ui.theme._6072300125_NajwaShaqilaIsnaN_asesment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _6072300125_NajwaShaqilaIsnaN_asesment1Theme {
                SetupNavGraph()
                }
            }
        }
    }



