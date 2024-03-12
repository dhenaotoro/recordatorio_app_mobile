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


class AlimentosListaActivity : ComponentActivity() {

    private val alimentos = mutableListOf<String>()
    private val alimentosBorrados = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentos_lista)
        val backButtonClick = findViewById<ImageButton>(R.id.AtrasAlimentarButton1)
        backButtonClick.setOnClickListener {
            val intent = Intent(this, SeleccionarOpcionActivity::class.java)
            startActivity(intent)
        }
        val crearButtonClick = findViewById<ImageButton>(R.id.AgregarButton)
        crearButtonClick.setOnClickListener {
            val intent = Intent(this, CrearAlimentoActivity::class.java)
            startActivity(intent)
        }
        init();
    }

    fun init() {
        val table = findViewById<TableLayout>(R.id.table_main)
        val row_header: TableRow = TableRow(this)
        val tvh1 = TextView(this)
        tvh1.text = "Alimento"
        tvh1.width = 400
        tvh1.textSize = 16.0f
        tvh1.lineHeight = 31
        tvh1.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tvh1.setTextColor(Color.WHITE)
        val tvh2 = TextView(this)
        tvh2.text = "Accion"
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
                        "producto" -> this.alimentos.add(producto)
                        "producto_a_borrar" -> {
                            if(!alimentosBorrados.isEmpty()) {
                                this.alimentos.removeAll(this.alimentosBorrados)
                            }
                            this.alimentos.remove(producto)
                            this.alimentosBorrados.add(producto)
                        }
                        else -> {}
                    }
                }
            }
        } else {
            print("No hay extras")
        }

        this.alimentos.forEachIndexed { index: Int, value: String ->
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
                        .setTitle("Borrar alimento")
                        .setMessage("Estás seguro de borrar este alimento?") // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes,
                            DialogInterface.OnClickListener { dialog, which ->
                                val intent = Intent(this, AlimentosListaActivity::class.java)
                                intent.putExtra("producto_a_borrar", value)
                                startActivity(intent)
                            }) // A null listener allows the button to dismiss the dialog and take no further action.
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
            if (this.alimentos.isEmpty()) {
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
        const val BORRARALIMENTO = 1
    }

}