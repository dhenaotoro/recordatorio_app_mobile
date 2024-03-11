package com.recordatorioapp.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.recordatorioapp.myapplication.ui.theme.RecordatorioAppTheme

class SeleccionarOpcionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_opcion)
        val supplyButtonClick = findViewById<Button>(R.id.AlimentarButton)
        supplyButtonClick.setOnClickListener {
            val intent = Intent(this, AlimentosListaActivity::class.java)
            startActivity(intent)
        }
    }
}