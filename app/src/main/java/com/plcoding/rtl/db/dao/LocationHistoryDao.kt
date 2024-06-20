package com.plcoding.rtl.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.plcoding.rtl.db.entities.LocationHistory

@Dao
interface LocationHistoryDao {
    @Insert
    suspend fun addLocationHistory(location: LocationHistory)
    
    @Delete
    suspend fun deleteLocationHistory(location: LocationHistory)
    
    @Query("select * from location_history")
    suspend fun getAll(): List<LocationHistory>
}
