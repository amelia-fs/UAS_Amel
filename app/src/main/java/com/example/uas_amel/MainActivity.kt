package com.example.uas_amel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val img = arrayOf(R.drawable.images1, R.drawable.images2, R.drawable.images3, R.drawable.images4, R.drawable.images5)
    private val text = arrayOf("Cake Pop","Pancake Cake","Cake Cheese","Cake Red Velvet","Chocolate Cake")
    private val desc = arrayOf("Pop Buble","Cake Special","Cheese Dessert","Red Velvet","Chocolate")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_menu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(img,text,desc)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        val btncake: Button = findViewById(R.id.btntambahdata)
        val btnorder: Button = findViewById(R.id.btnbeli)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, Login::class.java))
        }
        btncake.setOnClickListener {
            val intent = Intent(this, Cake::class.java)
            startActivity(intent)
        }
        btnorder.setOnClickListener {
            val intent = Intent(this, Order::class.java)
            startActivity(intent)
        }
    }
}