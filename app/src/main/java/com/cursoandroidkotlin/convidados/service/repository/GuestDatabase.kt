package com.cursoandroidkotlin.convidados.service.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class GuestDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context) : GuestDatabase {
            if (!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestDB")
                    .allowMainThreadQueries()                                                       //o Room considera que a conxeão com o banco é pesado, é custosa, então ele não te deixa rodar na mesma thread que ta rodando sua aplicação. Então se vc tirar esse metodo, quando vc roda uma consulta, dá erro.
                    .build()
            }
            return INSTANCE
        }
    }
}