package com.jsj.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(type)
        parcel.writeDouble(longitude)
        parcel.writeDouble(latitude)
        parcel.writeString(signature)
        parcel.writeString(price)
        parcel.writeString(link)
        parcel.writeInt(score)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }
}