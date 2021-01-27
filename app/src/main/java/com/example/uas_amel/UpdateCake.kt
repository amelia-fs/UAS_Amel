package com.example.uas_amel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateCake : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperCake
    lateinit var inputId: EditText
    lateinit var inputNama: EditText
    lateinit var inputRasa: EditText
    lateinit var inputHarga: EditText
    lateinit var idg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_cake)
        inputId = findViewById(R.id.input_idu)
        inputNama = findViewById(R.id.input_namau)
        inputRasa = findViewById(R.id.input_rasau)
        inputHarga = findViewById(R.id.input_hargau)
        userDBHelper = DBHelperCake(this)
        val bundle =  intent.extras
        if (bundle != null){
            idg = bundle.getString("idk").toString()
            inputId.setText(bundle.getString("idk"))
            inputNama.setText(bundle.getString("namak"))
            inputRasa.setText(bundle.getString("rasak"))
            inputHarga.setText(bundle.getString("hargak"))
        }
        userDBHelper = DBHelperCake(this)
    }
    fun cancelData(v: View){
        val kembali = Intent(this, RvDbCake::class.java)
        startActivity(kembali)
    }
    fun updateData(v: View){
        var namain = inputNama.text.toString()
        var rasain = inputRasa.text.toString()
        var hargain = inputHarga.text.toString()
        var idin = idg
        userDBHelper.updateData(idin, namain, rasain, hargain)
        val kembali = Intent(this, RvDbCake::class.java)
        startActivity(kembali)
    }
}