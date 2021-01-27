package com.example.uas_amel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapter(private  val listDataku: ArrayList<DBModelCake>): RecyclerView.Adapter<DBAdapter.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvidku: TextView = itemV.findViewById(R.id.tv_idku)
        var tvnamaku: TextView = itemV.findViewById(R.id.tv_namaku)
        var tvrasaku: TextView = itemV.findViewById(R.id.tv_rasaku)
        var tvhargaku: TextView = itemV.findViewById(R.id.tv_hargaku)
        var btnDelete: Button = itemV.findViewById(R.id.btndelete)
        var btnUpdate: Button = itemV.findViewById(R.id.btnupdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cake, parent, false)
        return CardViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataku = listDataku[position]
        holder.tvidku.text = dataku.id
        holder.tvnamaku.text = dataku.nama
        holder.tvrasaku.text = dataku.rasa
        holder.tvhargaku.text = dataku.harga

        holder.btnDelete.setOnClickListener {
            var adapterDBHelper: DBHelperCake
            adapterDBHelper = DBHelperCake(holder.itemView.context)
            adapterDBHelper.deleteData(dataku.id)
            listDataku.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnUpdate.setOnClickListener {
            val pindahUp = Intent(holder.itemView.context, UpdateCake::class.java)
            val bundle = Bundle()
            bundle.putString("idk", dataku.id)
            bundle.putString("namak", dataku.nama)
            bundle.putString("rasak", dataku.rasa)
            bundle.putString("hargak", dataku.harga)
            pindahUp.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUp)
        }
    }

    override fun getItemCount(): Int {
        return listDataku.size
    }

}