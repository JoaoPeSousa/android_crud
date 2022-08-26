package com.tp1.room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tp1.room.database.models.Usuario

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsuario(usuario:Usuario)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readUsuarios():LiveData<List<Usuario>>

    @Update
    suspend fun updateUsuarios(usuario : Usuario)

    @Delete
    suspend fun deleteUsuarios(usuario : Usuario)

    @Query("DELETE FROM user_table")
    fun deleteAll()


}