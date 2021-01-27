package com.example.uas_amel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Order : AppCompatActivity() {
    lateinit var userDBHelperr: DBHelper
    lateinit var inputIdd: EditText
    lateinit var inputNamaCus: EditText
    lateinit var inputNamaa: EditText
    lateinit var inputRasaa: EditText
    lateinit var inputJumlah: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        inputIdd = findViewById(R.id.input_ido)
        inputNamaCus = findViewById(R.id.input_namao)
        inputNamaa = findViewById(R.id.input_kueo)
        inputRasaa = findViewById(R.id.input_rasao)
        inputJumlah = findViewById(R.id.input_jumlaho)
        userDBHelperr = DBHelper(this)
    }
    fun addDataOrder(v: View){
        var idinn = inputIdd.text.toString()
        var namacusinn = inputNamaCus.text.toString()
        var namainn = inputNamaa.text.toString()
        var rasainn = inputRasaa.text.toString()
        var jumlahin = inputJumlah.text.toString()
        userDBHelperr.insertDataOrder(idinn, namacusinn, namainn, rasainn, jumlahin)
//        inputIdd.setText("")
//        inputNamaCus.setText("")
//        inputNamaa.setText("")
//        inputRasaa.setText("")
//        inputJumlah.setText("")
    }
    fun showAllOrder(v: View){
        var pindahh = Intent(this, RvDbOrder::class.java)
        startActivity(pindahh)
    }
}