package com.plcoding.rtl.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_history")
data class LocationHistory(
    val latitude: String,
    val longitude: String,
    val time: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}