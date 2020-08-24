package com.cursoandroidkotlin.convidados.service.repository

import com.cursoandroidkotlin.convidados.service.model.GuestModel

class GuestRepository {

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