package com.example.uas_amel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class Cake2 : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperCake
    lateinit var tampil: TextView
    lateinit var  ltampil: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cake2)
        userDBHelper = DBHelperCake(this)
        tampil = findViewById(R.id.tampil_semua)
        ltampil = findViewById(R.id.ltampil)
        var tampilkan = userDBHelper.fullData()
        //      ltampil.removeAllViews()
        tampilkan.forEach {
            tampil.text = tampil.text.toString() + " " + it.id.toString() + " - " + it.nama.toString() + " - " + it.rasa.toString() + " - " +
                    it.harga.toString() +  "\n"
            //         ltampil.addView(tampil)
        }
    }
}