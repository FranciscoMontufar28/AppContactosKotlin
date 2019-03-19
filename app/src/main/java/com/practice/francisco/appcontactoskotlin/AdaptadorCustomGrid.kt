package com.practice.francisco.appcontactoskotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorCustomGrid(var context: Context, items:ArrayList<Contacto>):BaseAdapter(){

    var items:ArrayList<Contacto>? = null
    var copiaItems:ArrayList<Contacto>? = null

    init {
        this.items = ArrayList(items)
        this.copiaItems = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder? = null
        var vista:View? = convertView

        if (vista == null){
            vista = LayoutInflater.from(context).inflate(R.layout.template_contacto_grid, null)
            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder
        }else{
            viewHolder = vista.tag as? ViewHolder
        }

        //Asignacion de valores a elementos graficos.
        val items = getItem(position) as Contacto
        viewHolder?.nombre?.text = items.nombre +" "+ items.apellidos
        viewHolder?.foto?.setImageResource(items.foto)

        return vista!!

    }

    override fun getItem(position: Int): Any {
        return this.items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        //Regresar el numero de elementos de la lista
        return this.items?.count()!!
    }

    fun addItem(item:Contacto){
        copiaItems?.add(item)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()

    }

    fun removeItem(index:Int){
        copiaItems?.removeAt(index)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun updateItem(index: Int, newItem:Contacto){
        copiaItems?.set(index, newItem)
        items = ArrayList(copiaItems)
        notifyDataSetChanged()
    }

    fun filtrar(str:String){
        items?.clear()
        if(str.isEmpty()){
            items = ArrayList(copiaItems)
            notifyDataSetChanged()
            return
        }
        var busqueda = str
        busqueda = busqueda.toLowerCase()
        for (item in copiaItems!!){
            val nombre = item.nombre.toLowerCase()
            if (nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }


    private class ViewHolder(view:View){
        var nombre: TextView? = null
        var foto: ImageView? = null


        init {
            nombre = view.findViewById(R.id.tvNombre)
            foto = view.findViewById(R.id.imagenContacto)
        }
    }

}