package com.example.uas_amel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Cake : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperCake
    lateinit var inputId: EditText
    lateinit var inputNama: EditText
    lateinit var inputRasa: EditText
    lateinit var inputHarga: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cake)
        inputId = findViewById(R.id.input_id)
        inputNama = findViewById(R.id.input_nama)
        inputRasa = findViewById(R.id.input_rasa)
        inputHarga = findViewById(R.id.input_harga)
        userDBHelper = DBHelperCake(this)
    }
    fun addData(v: View){
        var idin = inputId.text.toString()
        var namain = inputNama.text.toString()
        var rasain = inputRasa.text.toString()
        var hargain = inputHarga.text.toString()
        userDBHelper.insertData(idin, namain, rasain, hargain)
        inputId.setText("")
        inputNama.setText("")
        inputRasa.setText("")
        inputHarga.setText("")
        }
        fun showAll(v: View){
            var pindah = Intent(this, RvDbCake::class.java)
            startActivity(pindah)
        }
}