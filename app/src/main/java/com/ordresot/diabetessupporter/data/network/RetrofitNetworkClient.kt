package com.ordresot.diabetessupporter.data.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.ordresot.diabetessupporter.data.NetworkClient
import com.ordresot.diabetessupporter.data.dto.Response

class RetrofitNetworkClient(
    private val diabetesService: DiabetesApiService,
    private val connectivityManager: ConnectivityManager): NetworkClient {
    override fun makeRequest(dto: Any): Response {
        TODO("Not yet implemented")
    }

    override fun isConnected(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}