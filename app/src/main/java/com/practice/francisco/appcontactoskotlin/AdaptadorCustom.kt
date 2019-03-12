package com.practice.francisco.appcontactoskotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdaptadorCustom(var context: Context, items:ArrayList<Contacto>):BaseAdapter(){

    var items:ArrayList<Contacto>? = null

    init {
        this.items = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder? = null
        var vista:View? = convertView

        if (vista == null){
            vista = LayoutInflater.from(context).inflate(R.layout.template_contacto, null)
            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder
        }else{
            viewHolder = vista.tag as? ViewHolder
        }

        //Asignacion de valores a elementos graficos.
        val items = getItem(position) as Contacto
        viewHolder?.nombre?.text = items.nombre +" "+ items.apellidos
        viewHolder?.cargo?.text = items.cargo
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

    private class ViewHolder(view:View){
        var nombre:TextView? = null
        var foto:ImageView? = null
        var cargo:TextView? = null

        init {
            nombre = view.findViewById(R.id.tvNombre)
            foto = view.findViewById(R.id.imagenContacto)
            cargo = view.findViewById(R.id.tvOcupacion)
        }
    }
}