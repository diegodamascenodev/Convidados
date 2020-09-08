package com.cursoandroidkotlin.convidados.service.repository

import androidx.room.*
import com.cursoandroidkotlin.convidados.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel) : Long                                                              //Escolhi 'Long' pq quero que retorne um Id

    @Update
    fun update(guest: GuestModel) : Int                                                             //Escolhi 'Int' pq quero que retorne a quantidade de convidados atualizados

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")                                              //Essa variavel chamada 'id' acompanhada de dois pontos é o mesmo id esperado como parâmetro da função logo abaixo. Esses dois parâmetros SEMPRE tem que ter o mesmo nome.
    fun get(id: Int) : GuestModel

    @Query("SELECT * FROM Guest")
    fun getInvited() : List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresent() : List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsent() : List<GuestModel>
}