package com.cursoandroidkotlin.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.cursoandroidkotlin.convidados.service.constants.DataBaseConstants
import com.cursoandroidkotlin.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context){

    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()

    fun getAll(): List<GuestModel> {
        return mDatabase.getInvited()
    }

    fun get(id: Int) : GuestModel{
        return mDatabase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel) : Boolean {
        return mDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        return mDatabase.delete(guest)
    }

    fun getPresent(): List<GuestModel> {
        return mDatabase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDatabase.getAbsent()
    }
}