package com.practice.francisco.appcontactoskotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

class Nuevo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.iCrearNuevo ->{
                //crear nuevo elemento de tipo contacto
                val nombre = findViewById<EditText>(R.id.tvNombre)
                val apellidos = findViewById<EditText>(R.id.tvApellidos)
                val empresa =findViewById<EditText>(R.id.tvEmpresa)
                val edad = findViewById<EditText>(R.id.tvEdad)
                val peso = findViewById<EditText>(R.id.tvPeso)
                val telefono = findViewById<EditText>(R.id.tvTelefono)
                val direccion = findViewById<EditText>(R.id.tvDireccion)
                val cargo = findViewById<EditText>(R.id.tvCargo)
                val email = findViewById<EditText>(R.id.tvEmail)

                //validad campos


                MainActivity.agregarContacto(Contacto(nombre.text.toString(),apellidos.text.toString(),empresa.text.toString(),
                    cargo.text.toString(),edad.text.toString().toInt(),peso.text.toString().toFloat(),direccion.text.toString(),telefono.text.toString(),
                    email.text.toString(),R.drawable.foto_02))
                finish()
                Log.d("No ELEMENTOS", MainActivity.contactos?.count().toString())
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
