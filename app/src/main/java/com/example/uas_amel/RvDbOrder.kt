package com.example.uas_amel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvDbOrder : AppCompatActivity() {
    private lateinit var rv_tampilankuu: RecyclerView
    lateinit var userDBHelperr: DBHelperOrder
    private var listorder: ArrayList<DBModelOrder> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_db_order)
        rv_tampilankuu = findViewById(R.id.rv_tampilkano)
        rv_tampilankuu.setHasFixedSize(true)
        userDBHelperr = DBHelperOrder(this)
        listorder.addAll(userDBHelperr.fullDataa())
        rv_tampilankuu.layoutManager = LinearLayoutManager(this)
        var cardData = DBAdapterOrder(listorder)
        rv_tampilankuu.adapter = cardData
    }
}