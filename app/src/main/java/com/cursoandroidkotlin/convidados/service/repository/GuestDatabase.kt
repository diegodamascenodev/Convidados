package com.cursoandroidkotlin.convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cursoandroidkotlin.convidados.service.model.GuestModel

@Database(entities = arrayOf(GuestModel::class), version = 1)                                       //Estou informando quais são minhas entidades no primeiro parametro e qual é a versão do meu banco no segundo parâmetro.
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {

        private lateinit var INSTANCE: GuestDatabase

        fun getDatabase(context: Context) : GuestDatabase {

            if (!::INSTANCE.isInitialized){

                synchronized(GuestDatabase::class){
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestDB")
                        .allowMainThreadQueries()                                                   //o Room considera que a conxeão com o banco é pesado, é custosa, então ele não te deixa rodar na mesma thread que ta rodando sua aplicação. Então se vc tirar esse metodo, quando vc roda uma consulta, dá erro.
                        .build()
                }                                                                                   //Essa função é utilizada para evitar que essa thread seja processada mais de uma vez ao mesmo tempo como, por exemplo, no caso de um processador com dois núcleos cresolver que cada núcleo vai processar esse mesmo trecho de código.
            }
            return INSTANCE
        }
    }
}