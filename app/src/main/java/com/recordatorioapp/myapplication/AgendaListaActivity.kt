package com.recordatorioapp.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AgendaListaActivity : ComponentActivity() {

  private val agendas = mutableListOf<String>()
  private val agendasBorrados = mutableListOf<String>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_agendas_lista)

    val fechaActual = obtenerFechaActual()
    val fechaFormateada = formatearFecha(fechaActual)
    val textViewAlimentarTitle = findViewById<TextView>(R.id.AgendaTitle)
    textViewAlimentarTitle.text = fechaFormateada

    val backButtonClick = findViewById<ImageButton>(R.id.AtrasButton)
    backButtonClick.setOnClickListener {
      val intent = Intent(this, SeleccionarOpcionActivity::class.java)
      startActivity(intent)
    }
    val crearButtonClick = findViewById<ImageButton>(R.id.AgregarButton)
    crearButtonClick.setOnClickListener {
      val intent = Intent(this, CrearAgendaActivity::class.java)
      startActivity(intent)
    }
    init();
  }

  private fun obtenerFechaActual(): Date {
    val localeSpanish = Locale("es", "ES")
    return Date(System.currentTimeMillis())
  }

  private fun formatearFecha(fecha: Date): String {
    val formato = SimpleDateFormat("dd 'de' MMM 'del' yyyy", Locale("es", "ES"))
    return "Eventos de ${formato.format(fecha)}"
  }

  fun init() {
    val table = findViewById<TableLayout>(R.id.table_main)
    val row_header: TableRow = TableRow(this)
    val tvh1 = TextView(this)
    tvh1.text = "Nombre"
    tvh1.width = 400
    tvh1.textSize = 16.0f
    tvh1.lineHeight = 31
    tvh1.textAlignment = View.TEXT_ALIGNMENT_CENTER
    tvh1.setTextColor(Color.WHITE)
    val tvh2 = TextView(this)
    tvh2.text = "Eliminar"
    tvh2.width = 400
    tvh2.textSize = 16.0f
    tvh2.lineHeight = 31
    tvh2.textAlignment = View.TEXT_ALIGNMENT_CENTER
    tvh2.setTextColor(Color.WHITE)

    row_header.addView(tvh1)
    row_header.addView(tvh2)
    row_header.minimumHeight = 120
    row_header.minimumWidth = 120
    row_header.gravity = Gravity.CENTER_VERTICAL
    row_header.setBackgroundColor(resources.getColor(R.color.azul_claro_primario))
    table.addView(row_header, 0)

    val extras = intent.extras
    if (extras != null) {
      setOf("producto", "producto_a_borrar").forEach {
        val producto: String? = extras.getString(it)
        if (producto != null) {
          when (it) {
            "producto" -> this.agendas.add(producto)
            "producto_a_borrar" -> {
              if(!agendasBorrados.isEmpty()) {
                this.agendas.removeAll(this.agendasBorrados)
              }
              this.agendas.remove(producto)
              this.agendasBorrados.add(producto)
            }
            else -> {}
          }
        }
      }
    } else {
      print("No hay extras")
    }

    this.agendas.forEachIndexed { index: Int, value: String ->
      this.createRow {
        val row: TableRow = TableRow(this)
        val tv0 = TextView(this)
        tv0.text = value
        tv0.width = 400
        tv0.textSize = 16.0f
        tv0.lineHeight = 31
        tv0.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv0.setTextColor(Color.BLACK)
        row.addView(tv0)
        val frameLayout = LinearLayout(this)
        frameLayout.orientation = LinearLayout.HORIZONTAL
        frameLayout.gravity = Gravity.CENTER_HORIZONTAL
        frameLayout.minimumWidth = 24
        val removeButton = ImageButton(this)
        removeButton.setBackgroundResource(R.drawable.colorchange_eliminar)
        removeButton.setOnClickListener {
          print("Intenta eliminar item: $index - $value")
          AlertDialog.Builder(this)
            .setTitle("Borrar agenda")
            .setMessage("¿Estás seguro de borrar este registro?")
            .setPositiveButton(android.R.string.yes,
              DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, AgendaListaActivity::class.java)
                intent.putExtra("producto_a_borrar", value)
                startActivity(intent)
              })
            .setNegativeButton(android.R.string.no) { dialog, which ->
              dialog.cancel()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
        }
        frameLayout.addView(removeButton)
        row.addView(frameLayout)

        row.gravity = Gravity.CENTER_VERTICAL
        row.minimumHeight = 120
        table.addView(row, index + 1)
      }
    }.also {
      if (this.agendas.isEmpty()) {
        this.createRow {
          val row: TableRow = TableRow(this)
          val tv0 = TextView(this)
          tv0.text = "No hay elementos"
          tv0.width = 400
          tv0.textSize = 16.0f
          tv0.lineHeight = 31
          tv0.textAlignment = View.TEXT_ALIGNMENT_CENTER
          tv0.setTextColor(Color.BLACK)
          row.addView(tv0)
          val frameLayout = LinearLayout(this)
          frameLayout.orientation = LinearLayout.HORIZONTAL
          frameLayout.minimumWidth = 24
          row.addView(frameLayout)

          row.gravity = Gravity.CENTER_VERTICAL
          row.minimumHeight = 120
          table.addView(row,  1)
        }
      }
    }

  }

  fun createRow(buildRowOut: () -> Unit) {
    buildRowOut()
  }

  companion object {
    const val BORRAR_AGENDA = 1
  }

}