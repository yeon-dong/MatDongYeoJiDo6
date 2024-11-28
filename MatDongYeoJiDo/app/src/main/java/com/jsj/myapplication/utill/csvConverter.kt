package com.jsj.myapplication.utill

import android.content.Context
import android.util.Log
import com.jsj.myapplication.data.model.Place
import java.io.BufferedReader
import java.io.InputStreamReader

object csvConverter {
    fun readCSV(context: Context): List<Place> {
        val places = mutableListOf<Place>()
        val inputStream = context.resources.openRawResource(com.jsj.myapplication.R.raw.place) // CSV 파일 경로
        val reader = BufferedReader(InputStreamReader(inputStream))

        // 첫 번째 라인(헤더)은 건너뛰기
//        reader.readLine()

        reader.forEachLine { line ->
            val columns = line.split(",").map { it.trim() }
            if (columns.size < 10) {
                Log.e("CSV", "Invalid row: $line")
                return@forEachLine
            }
            if (line.isBlank()) return@forEachLine
//            val columns = line.split(",")
            val location = columns[0]
            val name = columns[1]
            val type = columns[2]
            val longitude = columns[3].toDouble()
            val latitude = columns[4].toDouble()
            val signature = columns[5]
            val price = columns[6]
            val link = columns[7]
            val score = columns[8].toInt()
            val comment = columns[9]

            places.add(Place(
                name = name,
                location = location,
                type = type,
                longitude = longitude,
                latitude = latitude,
                signature = signature,
                price = price,
                link = link,
                score = score,
                comment = comment
            ))
        }
        reader.close()
        return places
    }

}