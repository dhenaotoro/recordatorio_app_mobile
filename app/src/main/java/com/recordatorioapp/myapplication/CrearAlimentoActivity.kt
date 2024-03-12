package com.recordatorioapp.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CrearAlimentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alimento)
        val backButtonOnClick = findViewById<ImageButton>(R.id.AtrasCrearAlimentoButton1)
        backButtonOnClick.setOnClickListener{
            val intent = Intent(this, AlimentosListaActivity::class.java)
            startActivity(intent)
        }
        val cameraButtonOnClick = findViewById<ImageButton>(R.id.OpenCamera)
        cameraButtonOnClick.setOnClickListener {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERAPICREQUEST)
        }
        val agregarButtonOnClick = findViewById<Button>(R.id.CrearAlimentoButton)
        agregarButtonOnClick.setOnClickListener {
            val intent = Intent(this, AlimentosListaActivity::class.java)
            val producto = findViewById<TextInputEditText>(R.id.ProductoInputText)
            intent.putExtra("producto", producto.text.toString());
            startActivity(intent)
        }
        val mTuProductoCaduca = findViewById<TextInputEditText>(R.id.ProductoCaducaInputText)
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm aa", Locale("es", "CO")).format(Date())
        mTuProductoCaduca.setText(date)
        mTuProductoCaduca.setInputType(InputType.TYPE_NULL)
        mTuProductoCaduca.setKeyListener(null)
        mTuProductoCaduca.setOnClickListener { openDatePicker(mTuProductoCaduca) }

        val mTuRecordatorioIniciara = findViewById<TextInputEditText>(R.id.RecordatorioIniciaraInputText)
        mTuRecordatorioIniciara.setText(date)
        mTuRecordatorioIniciara.setInputType(InputType.TYPE_NULL)
        mTuRecordatorioIniciara.setKeyListener(null)
        mTuRecordatorioIniciara.setOnClickListener { openDatePicker(mTuRecordatorioIniciara) }

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.RecordatorioRepetirInputText)
        autoCompleteTextView.setThreshold(1);
        val items = arrayOf("Una vez después", "Dos veces después", "Una vez antes y una después", "Dos veces antes")
        val autoAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.list_item, items)
        autoCompleteTextView.setAdapter(autoAdapter)
    }

    @Deprecated("Using an override method but it's deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERAPICREQUEST && resultCode == RESULT_OK) {
            //imageView.setImageBitmap(attr.data.getExtras().get("data"))
            val codigoBarras = findViewById<TextInputEditText>(R.id.CodigoBarrasInputText)
            codigoBarras.setText(R.string.foto_tomada)
        }
    }

    private fun openDatePicker(input: TextInputEditText) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.addOnPositiveButtonClickListener {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd hh:mm aa", Locale("es", "CO")).format(it)
            input.setText(formattedDate)
        }
        datePicker.show(this.supportFragmentManager, "DATEPICKER")
    }

    companion object {
        const val CAMERAPICREQUEST = 1
    }
}