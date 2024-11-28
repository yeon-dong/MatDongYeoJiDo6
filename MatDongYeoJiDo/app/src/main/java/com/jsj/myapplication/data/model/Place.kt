package com.jsj.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class Place (
    @PrimaryKey val name: String,
    val location: String,
    val type: String,
    val longitude: Double = 0.0, // 기본값
    val latitude: Double = 0.0,  // 기본값
    val signature: String = "", // 기본값
    val price: String = "",     // 기본값
    val link: String = "",      // 기본값
    val score: Int = 0,         // 기본값
    val comment: String = ""    // 기본값
)
