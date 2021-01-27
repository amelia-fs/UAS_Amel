package com.example.uas_amel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val img:Array<Int>, val text: Array<String>, val desc: Array<String>):RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    class CustomViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        fun bindValue(image: Int, text: String, desc: String){
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(image)
            itemView.findViewById<TextView>(R.id.text1).text = text
            itemView.findViewById<TextView>(R.id.text2).text = desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return img.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindValue(img[position],text[position],desc[position])
    }
}