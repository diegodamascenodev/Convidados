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

    fun update(guest: GuestModel) : Boolean {
        try{
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"                             //Quem é o ponto de interrogação? É o valor da variavel "args", definida na linha de baixo
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)

            return true

        } catch (e: Exception) {
            return false
        }
    }

    fun delete(guest: GuestModel) : Boolean {
        try{
            val db = mGuestDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"                             //Quem é o ponto de interrogação? É o valor da variavel "args", definida na linha de baixo
            val args = arrayOf(guest.id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)

            return true

        } catch (e: Exception) {
            return false
        }
    }

    fun get(id: Int) : GuestModel?{

        var guest: GuestModel? = null

        try{
            val db = mGuestDataBaseHelper.readableDatabase

            /*********** QUERY SQL MAIS SEGURA USANDO A FUNÇÃO query() *************/

            val columns = arrayOf(DataBaseConstants.GUEST.TABLE_NAME,
                                     DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"                             //Quem é o ponto de interrogação? É o valor da variavel "args", definida na linha de baixo
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                null)
            /************************************************************************/

            /********** QUERY SQL MENOS SEGURA USANDO A FUNÇÃO rawQuery() ***********/
            /*                                                                      */
            /* val cursor = db.rawQuery("select * from Guest where id = $id", null) */
            /************************************************************************/

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()
                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }

            cursor?.close()

            return guest

        } catch (e: Exception) {
            return guest
        }
    }

    fun getAll(): List<GuestModel> {

        var list: MutableList<GuestModel> = ArrayList()

        try{
            val db = mGuestDataBaseHelper.readableDatabase

            /*********** QUERY SQL MAIS SEGURA USANDO A FUNÇÃO query() *************/

            val columns = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.TABLE_NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null)
            /************************************************************************/

            /********** QUERY SQL MENOS SEGURA USANDO A FUNÇÃO rawQuery() ***********/
            /*                                                                      */
            /* val cursor = db.rawQuery("SELECT id, name , presence FROM Guest WHERE presence = 1", null) */
            /************************************************************************/

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }
            cursor?.close()

            return list

        } catch (e: Exception) {
            return list
        }
    }

    fun getPresent(): List<GuestModel> {
        var list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        var list: MutableList<GuestModel> = ArrayList()

        try{
            val db = mGuestDataBaseHelper.readableDatabase

            /*********** QUERY SQL MAIS SEGURA USANDO A FUNÇÃO query() *************/

            val columns = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.TABLE_NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null)
            /************************************************************************/

            /********** QUERY SQL MENOS SEGURA USANDO A FUNÇÃO rawQuery() ***********/
            /*                                                                      */
            /* val cursor = db.rawQuery("SELECT id, name , presence FROM Guest WHERE presence = 0", null) */
            /************************************************************************/

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val absent = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 0)

                    val guest = GuestModel(id, name, absent)
                    list.add(guest)
                }
            }
            cursor?.close()

            return list

        } catch (e: Exception) {
            return list
        }
    }
}