package ru.emba.cbs.data.services

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class AndroidNetworkService @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ru.emba.cbs.domain.services.NetworkService {
    override fun isNetworkAvailable(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}