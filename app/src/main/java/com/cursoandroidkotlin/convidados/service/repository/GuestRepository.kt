package com.cursoandroidkotlin.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.cursoandroidkotlin.convidados.service.constants.DataBaseConstants
import com.cursoandroidkotlin.convidados.service.model.GuestModel
import java.lang.Exception

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

    fun save(guest: GuestModel): Boolean {
        try{
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            return true
        } catch (e: Exception) {
            return false
        }

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