package com.tp1.room.repository

import androidx.lifecycle.LiveData
import com.tp1.room.database.dao.UserDao
import com.tp1.room.database.models.Usuario

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<Usuario>> = userDao.readUsuarios()

    suspend fun addUsuario(usuario: Usuario){
        userDao.addUsuario(usuario)
    }

    suspend fun updateUsuario(usuario:Usuario){
        userDao.updateUsuarios(usuario)
    }


    suspend fun deleteUsuario(usuario: Usuario){
        userDao.deleteUsuarios(usuario)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }

}