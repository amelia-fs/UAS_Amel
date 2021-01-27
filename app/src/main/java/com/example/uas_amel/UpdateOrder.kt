package com.example.uas_amel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateOrder : AppCompatActivity() {
    lateinit var userDBHelperr: DBHelperOrder
    lateinit var inputIdd: EditText
    lateinit var inputNamaCus: EditText
    lateinit var inputNamaa: EditText
    lateinit var inputRasaa: EditText
    lateinit var inputJumlah: EditText
    lateinit var idm: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_order)
        inputIdd = findViewById(R.id.input_idorder)
        inputNamaCus = findViewById(R.id.input_namacustomer)
        inputNamaa = findViewById(R.id.input_namakue)
        inputRasaa = findViewById(R.id.input_rasakue)
        inputJumlah = findViewById(R.id.input_jumlahkue)
        userDBHelperr = DBHelperOrder(this)
        val bundle =  intent.extras
        if (bundle != null){
            idm = bundle.getString("idk").toString()
            inputIdd.setText(bundle.getString("idkk"))
            inputNamaCus.setText(bundle.getString("namacusk"))
            inputNamaa.setText(bundle.getString("namakk"))
            inputRasaa.setText(bundle.getString("rasakk"))
            inputJumlah.setText(bundle.getString("jumlahk"))
        }
        userDBHelperr = DBHelperOrder(this)
    }
    fun cancelDataOrder(v: View){
        val kembali = Intent(this, RvDbOrder::class.java)
        startActivity(kembali)
    }
    fun updateDataOrder(v: View){
        var namacusin = inputNamaCus.text.toString()
        var namainn = inputNamaa.text.toString()
        var rasainn = inputRasaa.text.toString()
        var jumlahin = inputJumlah.text.toString()
        var idin = idm
        userDBHelperr.updateData(idin, namacusin, namainn, rasainn, jumlahin)
        val kembali = Intent(this, RvDbOrder::class.java)
        startActivity(kembali)
    }
}