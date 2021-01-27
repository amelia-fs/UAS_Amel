package com.example.uas_amel

import android.provider.BaseColumns

object DBInfoCake {
    class CakeTable: BaseColumns {
        companion object {
            val TABLE_NAME = "Cake"
            val COL_ID = "id"
            val COL_NAME = "nama"
            val COL_TASTE = "rasa"
            val COL_PRICE = "harga"
        }
    }
}