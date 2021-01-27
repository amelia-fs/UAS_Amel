package com.example.uas_amel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class Order2 : AppCompatActivity() {
    lateinit var userDBHelperr: DBHelperOrder
    lateinit var tampill: TextView
    lateinit var  ltampill: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order2)
        userDBHelperr = DBHelperOrder(this)
        tampill = findViewById(R.id.tampil_semuao)
        ltampill = findViewById(R.id.ltampilo)
        var tampilkan = userDBHelperr.fullDataa()
        //      ltampil.removeAllViews()
        tampilkan.forEach {
            tampill.text = tampill.text.toString() + " " + it.idorder.toString() + " - " + it.namacustomer.toString() + " - " + it.namaorder.toString() + " - " + it.rasaorder.toString() + " - " +
                    it.jumlahorder.toString() +  "\n"
            //         ltampil.addView(tampil)
        }
    }
}