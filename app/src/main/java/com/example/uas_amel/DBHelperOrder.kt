package com.example.uas_amel

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperOrder(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "Order.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoOrder.OrderTable.TABLE_NAMEORDER + " (" + DBInfoOrder.OrderTable.COL_IDORDER +
                    " VARCHAR(100) PRIMARY KEY, " + DBInfoOrder.OrderTable.COL_NAMECUS + " TEXT, " + DBInfoOrder.OrderTable.COL_NAMEORDER + " TEXT, " +
                    DBInfoOrder.OrderTable.COL_TASTEORDER + " TEXT, " + DBInfoOrder.OrderTable.COL_QTYORDER + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + DBInfoOrder.OrderTable.TABLE_NAMEORDER
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun insertDataOrder(idinn: String, namacusinn: String, namainn: String, rasainn: String, jumlahinn: String){
        val dbb = writableDatabase
        val namatablett = DBInfoOrder.OrderTable.TABLE_NAMEORDER
        val idtt = DBInfoOrder.OrderTable.COL_IDORDER
        val namacustt = DBInfoOrder.OrderTable.COL_NAMEORDER
        val namatt = DBInfoOrder.OrderTable.COL_NAMEORDER
        val rasatt = DBInfoOrder.OrderTable.COL_TASTEORDER
        val jumlahtt = DBInfoOrder.OrderTable.COL_QTYORDER
        var sql = "INSERT INTO "+ namatablett +"("+idtt+", "+namacustt+", "+namatt+", "+rasatt+", "+jumlahtt+") " +
                "VALUES('"+idinn+"','"+namacusinn+"', '"+namainn+"', '"+rasainn+"', '"+jumlahinn+"')"
        dbb.execSQL(sql)
    }
    fun fullDataa():ArrayList<DBModelOrder>{
        //val caker = ArrayList<DBModelCake>()
        val orderr =  arrayListOf<DBModelOrder>()
        val dbb = writableDatabase
        var cursorr: Cursor? = null
        try{
            cursorr = dbb.rawQuery("SELECT * FROM "+ DBInfoOrder.OrderTable.TABLE_NAMEORDER, null)
        }catch (e: SQLException){
            dbb.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var idtt: String
        var namacustt: String
        var namatt: String
        var rasatt: String
        var jumlahtt: String
        if (cursorr!!.moveToFirst()){
            while (cursorr.isAfterLast==false){
                idtt = cursorr.getString(cursorr.getColumnIndex(DBInfoOrder.OrderTable.COL_IDORDER))
                namacustt = cursorr.getString(cursorr.getColumnIndex(DBInfoOrder.OrderTable.COL_NAMECUS))
                namatt = cursorr.getString(cursorr.getColumnIndex(DBInfoOrder.OrderTable.COL_NAMEORDER))
                rasatt = cursorr.getString(cursorr.getColumnIndex(DBInfoOrder.OrderTable.COL_TASTEORDER))
                jumlahtt = cursorr.getString(cursorr.getColumnIndex(DBInfoOrder.OrderTable.COL_QTYORDER))

                orderr.add(DBModelOrder(idtt, namacustt, namatt, rasatt, jumlahtt))
                cursorr.moveToNext()
            }
        }
        return orderr
    }
    fun deleteData(idinn: String){
        val dbb = writableDatabase
        val namatablett = DBInfoOrder.OrderTable.TABLE_NAMEORDER
        val idtt = DBInfoOrder.OrderTable.COL_IDORDER
        val sql = "DELETE FROM " +namatablett+ " WHERE " +idtt+"='"+idinn+"'"
        dbb.execSQL(sql)
    }
    fun updateData(idinn: String, namacusinn: String, namainn: String, rasainn: String, jumlahinn: String){
        val dbb = writableDatabase
        val namatablett = DBInfoOrder.OrderTable.TABLE_NAMEORDER
        val idtt = DBInfoOrder.OrderTable.COL_IDORDER
        val namacustt = DBInfoOrder.OrderTable.COL_NAMECUS
        val namatt = DBInfoOrder.OrderTable.COL_NAMEORDER
        val rasatt = DBInfoOrder.OrderTable.COL_TASTEORDER
        val jumlahtt = DBInfoOrder.OrderTable.COL_QTYORDER
        var sql = "UPDATE "+ namatablett + " SET "+
                namacustt+"='"+namacusinn+"', "+namatt+"='"+namainn+"', "+rasatt+"='"+rasainn+"', "+jumlahtt+"='"+jumlahinn+"' "+
                "WHERE "+idtt+"='"+idinn+"'"
        dbb.execSQL(sql)
    }
}