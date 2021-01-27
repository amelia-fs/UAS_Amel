package com.example.uas_amel

import android.provider.BaseColumns

object DBInfoOrder {
    class OrderTable: BaseColumns {
        companion object {
            val TABLE_NAMEORDER = "order"
            val COL_IDORDER = "idorder"
            val COL_NAMECUS = "namacus"
            val COL_NAMEORDER = "namaorder"
            val COL_TASTEORDER = "rasaorder"
            val COL_QTYORDER = "jumlahorder"
        }
    }
}