package com.example.uas_amel

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import java.sql.SQLException
import java.sql.SQLTimeoutException

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JENKAL + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
        private val SQL_CREATE_ORDER = "CREATE TABLE " + DBInfoOrder.OrderTable.TABLE_NAMEORDER + " (" + DBInfoOrder.OrderTable.COL_IDORDER +
                    " VARCHAR(100) PRIMARY KEY, " + DBInfoOrder.OrderTable.COL_NAMECUS + " TEXT, " + DBInfoOrder.OrderTable.COL_NAMEORDER + " TEXT, " +
                    DBInfoOrder.OrderTable.COL_TASTEORDER + " TEXT, " + DBInfoOrder.OrderTable.COL_QTYORDER + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_ORDER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jenkalt = DBInfo.UserTable.COL_JENKAL
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ", " + jenkalt + ", " + alamatt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
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
        }catch (e: android.database.SQLException){
            dbb.execSQL(DBHelper.SQL_CREATE_ORDER)
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