package com.recordatorioapp.myapplication

import android.os.Bundle
import android.text.InputType
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText


class AlimentoCrearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alimento)
        val mTextInputEditText = findViewById<TextInputEditText>(R.id.UserInputText)
        mTextInputEditText.setInputType(InputType.TYPE_NULL)
        mTextInputEditText.setKeyListener(null)
        mTextInputEditText.setOnClickListener { openDatePicker() }
    }

    fun openDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.show(this.getSupportFragmentManager(), "DATEPICKER");
    }
}