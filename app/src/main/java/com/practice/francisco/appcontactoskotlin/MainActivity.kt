package com.practice.francisco.appcontactoskotlin

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Switch
import android.widget.ViewSwitcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null
    var switcherView:ViewSwitcher? =null

    companion object {
        var adaptador:AdaptadorCustom? = null
        var contactos:ArrayList<Contacto>? = null
        fun agregarContacto(contacto:Contacto){
            //contactos?.add(contacto)
            adaptador?.addItem(contacto)
        }
        fun obtenerContacto(index:Int):Contacto{
            //return contactos?.get(index)!!
            return adaptador?.getItem(index) as Contacto
        }
        fun eliminarContacto(index:Int){
            //contactos?.removeAt(index)
            adaptador?.removeItem(index)
        }
        fun actualizarContactos(index: Int, nuevoContacto:Contacto){
            //contactos?.set(index, nuevoContacto)
            adaptador?.updateItem(index, nuevoContacto)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        contactos = ArrayList()
        contactos?.add(Contacto("Francisco","Montúfar","Globant","Android Developer",24,70.0F,"Bogota DC","123456789","franciscomontufar@globant.com",R.drawable.foto_01))
        contactos?.add(Contacto("Daniela","Casas","Google Inc","South Operativon leader",24,55.0F,"Bogota DC","123456789","danielacasas@google.com",R.drawable.foto_04))
        contactos?.add(Contacto("Edinson","Ortega","Seratic Ltda","Software Engineer",28,55.0F,"Popayan","123456789","edinsonortega@seratic.com",R.drawable.foto_03))
        contactos?.add(Contacto("Katherin","Escobar","Seratic Ltda","Software Engineer",23,55.0F,"Popayan","123456789","katherinescobar@seratic.com",R.drawable.foto_04))
        lista = findViewById<ListView>(R.id.lista)
        adaptador  = AdaptadorCustom(this, contactos!!)

        switcherView = findViewById(R.id.viewSwitcher)

        lista?.adapter = adaptador

        lista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.searchView)
        val searchView = itemBusqueda?.actionView as SearchView

        val itemSwitch = menu?.findItem(R.id.switchView)
        itemSwitch.setActionView(R.layout.switch_item)
        val switchView = itemSwitch?.actionView?.findViewById<Switch>(R.id.switchVista)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar Contacto..."

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //filtrar
                adaptador?.filtrar(newText!!)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //filtrar
                return true
            }
        })

        switchView?.setOnCheckedChangeListener { buttonView, isChecked ->
            viewSwitcher?.showNext()
        }

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
