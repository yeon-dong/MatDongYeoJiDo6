package com.jsj.myapplication.data.repository

import com.jsj.myapplication.data.database.AppDataBase
import com.jsj.myapplication.data.model.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepository(private val db: AppDataBase) {
    suspend fun insertPlaces(places: List<Place>) {
        withContext(Dispatchers.IO) {
            db.placeDao().insertPlaces(places)
        }
    }

    suspend fun getAllPlaces(): List<Place> {
        return withContext(Dispatchers.IO) {
            db.placeDao().getAllPlaces()
        }
    }
}