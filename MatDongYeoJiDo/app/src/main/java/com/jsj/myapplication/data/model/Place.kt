package com.jsj.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class Place (
    @PrimaryKey val name : String,
    val location : String,
    val type : String,
    val longitude : Double,
    val latitude : Double,
    val signature : String,
    val price : String,
    val link : String,
    val score : Int,
    val comment : String

)
