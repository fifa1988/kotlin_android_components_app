package com.example.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {

    var sql = arrayOf(
        "CREATE TABLE utilizador (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT) ",
        "INSERT INTO utilizador (username, password) VALUES ('user', 'pass') ",
        "INSERT INTO utilizador (username, password) VALUES ('admin', 'pass') "
    )
    override fun onCreate(db: SQLiteDatabase?) {
        sql.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE utilziador")
        onCreate(db)
    }

    fun utilizarInsert(username:String,password:String): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = db.insert("utilizador", null, contentValues)
        db.close()
        return result
    }
    fun utilizarUpdate(id:Int, username:String,password:String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = db.update("utilizador", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return result
    }
    fun utilizarDelete(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete("utilizador", "id=?", arrayOf(id.toString()))
        db.close()
        return result
    }

    fun utilizadorSelectAll(): Cursor {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizador", null)
        db.close()
        return c
    }

    fun utilizadorSelectById(id: Int): Cursor{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizador where id=?", arrayOf(id.toString()))
        db.close()
        return c
    }

    fun utilizadorObjectSelectById(id :Int) : Utilizador{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizador where id=?", arrayOf(id.toString()))
        var utilizador = Utilizador()
        if(c.count == 1){
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val passwordIndex = c.getColumnIndex("password")
            val id = c.getInt(idIndex)
            val username = c.getString(usernameIndex)
            val password = c.getString(passwordIndex)
            utilizador = Utilizador(id, username, password)
        }
        db.close()
        return utilizador
    }

    fun utilizadorListSelectAll(): ArrayList<Utilizador> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM utilizador", null)
        val listaUtilizador: ArrayList<Utilizador> = ArrayList()
        if(c.count > 0){
            c.moveToFirst()
            do {
                val idIndex = c.getColumnIndex("id")
                val usernameIndex = c.getColumnIndex("username")
                val passwordIndex = c.getColumnIndex("password")
                val id = c.getInt(idIndex)
                val username = c.getString(usernameIndex)
                val password = c.getString(passwordIndex)
                listaUtilizador.add(Utilizador(id, username, password))
            }while (c.moveToNext())
            db.close()
        }
        return listaUtilizador
    }
}