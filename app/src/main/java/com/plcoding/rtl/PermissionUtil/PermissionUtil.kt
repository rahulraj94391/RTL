package com.plcoding.rtl.PermissionUtil

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_SMS
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_DENIED
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


class PermissionUtil() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    
    private var sms = -1 // 0 if granted
    private var extStorage = -1
    private var contacts = -1
    private var location = -1
    var sum = 0
        private set
    
    companion object {
        fun sms(context: Context): Int = ContextCompat.checkSelfPermission(context, READ_SMS)
        fun extStorage(context: Context): Int = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE)
        fun contacts(context: Context): Int = ContextCompat.checkSelfPermission(context, READ_CONTACTS)
        fun location(context: Context): Int = ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
    }
    
    fun checkAndAskPermission(activity: ComponentActivity, openAppSettings: (Boolean) -> Unit) {
        permissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            sms = if (permission[READ_SMS] == true) 0 else -1
            extStorage = if (permission[READ_EXTERNAL_STORAGE] == true) 0 else -1
            contacts = if (permission[READ_CONTACTS] == true) 0 else -1
            location = if (permission[ACCESS_FINE_LOCATION] == true) 0 else -1
        }
        
        checkPermission(activity)
        askPermission()
        
        sum = sms + extStorage + contacts + location
        if (sum != 0) {
            openAppSettings(true)
        }
    }
    
    private fun checkPermission(context: Context) {
        sms = sms(context)
        extStorage = extStorage(context)
        contacts = contacts(context)
        location = location(context)
    }
    
    private fun askPermission() {
        val permissionRequest: MutableList<String> = ArrayList()
        
        // SMS
        if (sms == PERMISSION_DENIED) {
            permissionRequest.add(READ_SMS)
        }
        
        
        // EXT_Storage
        if (extStorage == PERMISSION_DENIED) {
            permissionRequest.add(READ_EXTERNAL_STORAGE)
        }
        
        
        // Contacts
        if (contacts == PERMISSION_DENIED) {
            permissionRequest.add(READ_CONTACTS)
        }
        
        
        // Location
        if (location == PERMISSION_DENIED) {
            permissionRequest.add(ACCESS_FINE_LOCATION)
        }
        
        if (permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }
}