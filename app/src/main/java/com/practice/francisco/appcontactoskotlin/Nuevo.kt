package com.practice.francisco.appcontactoskotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_nuevo.*

class Nuevo : AppCompatActivity() {

    var fotoIndex:Int = 0
    val fotos = arrayOf(R.drawable.foto_01,R.drawable.foto_02,R.drawable.foto_03,R.drawable.foto_04,R.drawable.foto_05,
        R.drawable.foto_06)
    var foto:ImageView? = null
    var index:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        foto = findViewById<ImageView>(R.id.ivFoto)
        foto?.setOnClickListener{
            seleccionarFoto()
        }
        //Reconocer accion de nuevo vs editar
        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID").toInt()
            rellenarDatos(index)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                finish()
                return true
            }

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

                if(index > -1){
                    MainActivity.actualizarContactos(index, Contacto(nombre.text.toString(),apellidos.text.toString(),empresa.text.toString(),
                        cargo.text.toString(),edad.text.toString().toInt(),peso.text.toString().toFloat(),direccion.text.toString(),telefono.text.toString(),
                        email.text.toString(),obtenerFoto(fotoIndex)))
                }
                MainActivity.agregarContacto(Contacto(nombre.text.toString(),apellidos.text.toString(),empresa.text.toString(),
                    cargo.text.toString(),edad.text.toString().toInt(),peso.text.toString().toFloat(),direccion.text.toString(),telefono.text.toString(),
                    email.text.toString(),obtenerFoto(fotoIndex)))
                finish()
                Log.d("No ELEMENTOS", MainActivity.contactos?.count().toString())
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
    fun seleccionarFoto(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona imagen de perfil")

        val adaptadorDialogo = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        adaptadorDialogo.add("Foto 01")
        adaptadorDialogo.add("Foto 02")
        adaptadorDialogo.add("Foto 03")
        adaptadorDialogo.add("Foto 04")
        adaptadorDialogo.add("Foto 05")
        adaptadorDialogo.add("Foto 06")

        builder.setAdapter(adaptadorDialogo){
            dialog, which ->
            fotoIndex = which
            foto?.setImageResource(obtenerFoto(fotoIndex))
        }

        builder.setNegativeButton("Cancelar"){
            dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
    fun obtenerFoto(index:Int):Int{
        return fotos.get(index)
    }
    fun rellenarDatos(index: Int){
        val contacto = MainActivity.obtenerContacto(index)

        val tvNombre = findViewById<EditText>(R.id.tvNombre)
        val tvApellido = findViewById<EditText>(R.id.tvApellidos)
        val tvEmpresa = findViewById<EditText>(R.id.tvEmpresa)
        val tvEdad = findViewById<EditText>(R.id.tvEdad)
        val tvPeso = findViewById<EditText>(R.id.tvPeso)
        val tvTelefono = findViewById<EditText>(R.id.tvTelefono)
        val tvEmail = findViewById<EditText>(R.id.tvEmail)
        val tvDireccion = findViewById<EditText>(R.id.tvDireccion)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        tvNombre.setText(contacto.nombre, TextView.BufferType.EDITABLE)
        tvApellido.setText(contacto.apellidos, TextView.BufferType.EDITABLE)
        tvEmpresa.setText(contacto.empresa, TextView.BufferType.EDITABLE)
        tvEdad.setText(contacto.edad.toString(), TextView.BufferType.EDITABLE)
        tvPeso.setText(contacto.peso.toString(), TextView.BufferType.EDITABLE)
        tvTelefono.setText(contacto.telefono, TextView.BufferType.EDITABLE)
        tvEmail.setText(contacto.email, TextView.BufferType.EDITABLE)
        tvDireccion.setText(contacto.direction, TextView.BufferType.EDITABLE)
        tvCargo.setText(contacto.cargo,TextView.BufferType.EDITABLE)

        ivFoto.setImageResource(contacto.foto)

        var posicion = 0
        for (foto in fotos){
            if (contacto.foto == foto){
                fotoIndex = posicion
            }
            posicion++
        }
    }
}
