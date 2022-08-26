package com.tp1.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tp1.room.database.data.UserDatabase
import com.tp1.room.database.models.Usuario
import com.tp1.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application): AndroidViewModel(application) {

    private val repo : UserRepository
    val readAllData :LiveData<List<Usuario>>
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repo = UserRepository(userDao)
        readAllData = repo.readAllData
    }


    fun addUsuario(usuario:Usuario){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUsuario(usuario)
        }
    }

    fun updateUsuario(usuario:Usuario){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateUsuario(usuario)
        }
    }

    fun deleteUsuario(usuario : Usuario){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUsuario(usuario)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }

}