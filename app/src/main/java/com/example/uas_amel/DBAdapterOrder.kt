package com.example.uas_amel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapterOrder(private  val listOrder: ArrayList<DBModelOrder>): RecyclerView.Adapter<DBAdapterOrder.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvidkuu: TextView = itemV.findViewById(R.id.tv_idkuo)
        var tvnamacusku: TextView = itemV.findViewById(R.id.tv_namacusku)
        var tvnamakuu: TextView = itemV.findViewById(R.id.tv_namakuo)
        var tvrasakuu: TextView = itemV.findViewById(R.id.tv_rasakuo)
        var tvjumlahku: TextView = itemV.findViewById(R.id.tv_jumlahkuo)
        var btnDelete: Button = itemV.findViewById(R.id.btndeleteo)
        var btnUpdate: Button = itemV.findViewById(R.id.btnupdateo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return CardViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataorder = listOrder[position]
        holder.tvidkuu.text = dataorder.idorder
        holder.tvnamacusku.text = dataorder.namacustomer
        holder.tvnamakuu.text = dataorder.namaorder
        holder.tvrasakuu.text = dataorder.rasaorder
        holder.tvjumlahku.text = dataorder.jumlahorder

        holder.btnDelete.setOnClickListener {
            var adapterDBHelperr: DBHelperOrder
            adapterDBHelperr = DBHelperOrder(holder.itemView.context)
            adapterDBHelperr.deleteData(dataorder.idorder)
            listOrder.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnUpdate.setOnClickListener {
            val pindahUpp = Intent(holder.itemView.context, UpdateOrder::class.java)
            val bundle = Bundle()
            bundle.putString("idkk", dataorder.idorder)
            bundle.putString("namacusk", dataorder.namacustomer)
            bundle.putString("namakk", dataorder.namaorder)
            bundle.putString("rasakk", dataorder.rasaorder)
            bundle.putString("jumlahk", dataorder.jumlahorder)
            pindahUpp.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUpp)
        }
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }
}