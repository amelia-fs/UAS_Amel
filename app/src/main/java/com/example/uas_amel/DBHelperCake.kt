package com.example.uas_amel

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperCake(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "Cake.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoCake.CakeTable.TABLE_NAME + " (" + DBInfoCake.CakeTable.COL_ID +
                    " VARCHAR(100) PRIMARY KEY, " + DBInfoCake.CakeTable.COL_NAME + " TEXT, " +
                    DBInfoCake.CakeTable.COL_TASTE + " TEXT , " + DBInfoCake.CakeTable.COL_PRICE + " TEXT) "
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + DBInfoCake.CakeTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun insertData(idin: String, namain: String, rasain: String, hargain: String):Boolean{
        val db = writableDatabase
        val namatablet = DBInfoCake.CakeTable.TABLE_NAME
        val idt = DBInfoCake.CakeTable.COL_ID
        val namat = DBInfoCake.CakeTable.COL_NAME
        val rasat = DBInfoCake.CakeTable.COL_TASTE
        val hargat = DBInfoCake.CakeTable.COL_PRICE
        var sql = "INSERT INTO "+ namatablet +"("+idt+", "+namat+", "+rasat+", "+hargat+") " +
                "VALUES('"+idin+"', '"+namain+"', '"+rasain+"', '"+hargain+"')"
        db.execSQL(sql)
        return true
    }
    fun fullData():ArrayList<DBModelCake>{
        //val caker = ArrayList<DBModelCake>()
        val caker =  arrayListOf<DBModelCake>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery("SELECT * FROM "+ DBInfoCake.CakeTable.TABLE_NAME, null)
        }catch (e: SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var idt: String
        var namat: String
        var rasat: String
        var hargat: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idt = cursor.getString(cursor.getColumnIndex(DBInfoCake.CakeTable.COL_ID))
                namat = cursor.getString(cursor.getColumnIndex(DBInfoCake.CakeTable.COL_NAME))
                rasat = cursor.getString(cursor.getColumnIndex(DBInfoCake.CakeTable.COL_TASTE))
                hargat = cursor.getString(cursor.getColumnIndex(DBInfoCake.CakeTable.COL_PRICE))

                caker.add(DBModelCake(idt, namat, rasat, hargat))
                cursor.moveToNext()
            }
        }
        return caker
    }
    fun deleteData(idin: String){
        val db = writableDatabase
        val namatablet = DBInfoCake.CakeTable.TABLE_NAME
        val idt = DBInfoCake.CakeTable.COL_ID
        val sql = "DELETE FROM " +namatablet+ " WHERE " +idt+"='"+idin+"'"
        db.execSQL(sql)
    }
    fun updateData(idin: String, namain: String, rasain: String, hargain: String){
        val db = writableDatabase
        val namatablet = DBInfoCake.CakeTable.TABLE_NAME
        val idt = DBInfoCake.CakeTable.COL_ID
        val namat = DBInfoCake.CakeTable.COL_NAME
        val rasat = DBInfoCake.CakeTable.COL_TASTE
        val hargat = DBInfoCake.CakeTable.COL_PRICE
        var sql = "UPDATE "+ namatablet + " SET "+
                namat+"='"+namain+"', "+rasat+"='"+rasain+"', "+hargat+"='"+hargain+"' "+
                "WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
}