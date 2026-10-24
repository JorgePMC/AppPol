package com.example.apppol.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, "apppol.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE usuarios (
                rut TEXT PRIMARY KEY,
                nombre TEXT,
                correo TEXT,
                contrasena TEXT,
                regimiento TEXT,
                direccion TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    // Insertar un nuevo usuario en la base de datos
    fun registrarUsuario(
        rut: String,
        nombre: String,
        correo: String,
        pass: String,
        regimiento: String,
        direccion: String
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("rut", rut)
            put("nombre", nombre)
            put("correo", correo)
            put("contrasena", pass)
            put("regimiento", regimiento)
            put("direccion", direccion)
        }
        val resultado = db.insert("usuarios", null, values)
        db.close()
        return resultado != -1L // Retorna true si guardó correctamente
    }

    // Verificar si existe un usuario con ese correo y contraseña
    fun validarLogin(correo: String, pass: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?",
            arrayOf(correo.trim(), pass.trim())
        )
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}