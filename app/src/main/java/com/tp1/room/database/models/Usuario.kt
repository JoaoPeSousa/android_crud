package com.tp1.room.database.models

import android.os.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nome:String,
    val sobrenome:String,
    val idade :Int
    ): Parcelable