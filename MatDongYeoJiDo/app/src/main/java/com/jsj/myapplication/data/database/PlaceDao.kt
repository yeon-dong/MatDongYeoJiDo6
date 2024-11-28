package com.jsj.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsj.myapplication.data.model.Place

@Dao
interface PlaceDao {
    @Insert
    suspend fun insertPlaces(places: List<Place>)

    @Query("SELECT * FROM place")
    suspend fun getAllPlaces(): List<Place>
}