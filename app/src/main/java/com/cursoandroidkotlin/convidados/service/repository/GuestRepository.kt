package com.cursoandroidkotlin.convidados.service.repository

import android.content.Context
import com.cursoandroidkotlin.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context){

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context) : GuestRepository {
            if (!::repository.isInitialized){                                                       //Se o repositorio NÃO foi inicializado
                repository = GuestRepository(context)                                               //Inicializando o repositorio
            }
            return repository
        }
    }

    fun save(guest: GuestModel) {

    }

    fun update(guest: GuestModel) {

    }

    fun delete(guest: GuestModel) {

    }

    fun getAll(): List<GuestModel> {
        var list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        var list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        var list: MutableList<GuestModel> = ArrayList()
        return list
    }
}