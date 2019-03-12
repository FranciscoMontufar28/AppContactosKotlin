package com.practice.francisco.appcontactoskotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null
    var adaptador:AdaptadorCustom? = null

    companion object {
        var contactos:ArrayList<Contacto>? = null
        fun agregarContacto(contacto:Contacto){
            contactos?.add(contacto)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        contactos = ArrayList()
        contactos?.add(Contacto("Francisco","Montúfar","Globant","Android Developer",24,70.0F,"Bogota DC","123456789","franciscomontufar@globant.com",R.drawable.foto_01))
        lista = findViewById<ListView>(R.id.lista)
        adaptador  = AdaptadorCustom(this, contactos!!)
        lista?.adapter = adaptador
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.iNuevo ->{
               val intent = Intent(this, Nuevo::class.java)
                startActivity(intent)
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adaptador?.notifyDataSetChanged()
    }
}
