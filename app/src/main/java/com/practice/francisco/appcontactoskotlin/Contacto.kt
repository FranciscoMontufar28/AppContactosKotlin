package com.practice.francisco.appcontactoskotlin

class Contacto(nombre:String,apellidos:String,empresa:String,cargo:String,edad:Int,peso:Float,direction:String,telefono:String,email:String,foto:Int) {

    var nombre:String = ""
    var apellidos:String = ""
    var empresa:String = ""
    var cargo:String = ""
    var edad:Int = 0
    var peso:Float = 0.0F
    var direction:String = ""
    var telefono:String = ""
    var email:String = ""
    var foto:Int = 0

    init {
        this.nombre = nombre
        this.apellidos = apellidos
        this.empresa = empresa
        this.cargo = cargo
        this.edad = edad
        this.peso = peso
        this.direction = direction
        this.telefono = telefono
        this.email = email
        this.foto = foto
    }
}