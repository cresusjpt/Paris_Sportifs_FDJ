package tech.jeanpaultossou.fdj.parissportifs.core

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import tech.jeanpaultossou.fdj.parissportifs.core.Constants.TAG

fun isDeviceOnline(context: Context): Boolean {
    val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
    return if (networkCapabilities == null) {
        Log.d(TAG, "Offline")
        false
    } else {
        Log.d(TAG, "Online")
        true
    }
}