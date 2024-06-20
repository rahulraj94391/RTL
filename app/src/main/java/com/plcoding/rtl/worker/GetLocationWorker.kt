package com.plcoding.rtl.worker

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import com.plcoding.rtl.db.AppDatabase
import com.plcoding.rtl.db.entities.LocationHistory
import kotlinx.coroutines.tasks.await

class GetLocationWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    
    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        return try {
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)
            val location: Location? = fusedLocationProviderClient.lastLocation.await()
            var longitude = ""
            var latitude = ""
            location?.let {
                longitude = it.longitude.toString()
                latitude = it.latitude.toString()
            }
            val time = System.currentTimeMillis()
            AppDatabase.getDatabase(applicationContext).locationHistoryDao().addLocationHistory(LocationHistory(latitude, longitude, time))
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
