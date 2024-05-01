package com.example.app.data

import com.example.app.model.Carro

//Chama base de dados, api
class CarroMock {
    var listaCarros = ArrayList<Carro>()
    init {
        for (i in 0 .. 5){
            listaCarros.add(Carro(i, i.toString()))
        }
    }

}