package me.scraplesh.courses.data.services

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import me.scraplesh.courses.domain.services.NetworkService
import javax.inject.Inject

class AndroidNetworkService @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : NetworkService {
    override fun isNetworkAvailable(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}