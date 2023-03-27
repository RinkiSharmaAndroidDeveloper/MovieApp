package com.urbn.android.flickster.presentation.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val Tag = "MyTagConnectionManager"

class ConnectionLiveData(context: Context) : LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks : MutableSet<Network> = HashSet()

    private fun checkValidNetwork(){
        postValue(validNetworks.size > 0)
    }

    override fun onActive() {
        networkCallback = createNetworkCallBack()
    }

    private fun createNetworkCallBack() =object : ConnectivityManager.NetworkCallback(){
//        override fun onAvailable(network: Network) {
//            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
//            val hasNetworkCapabilities =networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
//            if(hasNetworkCapabilities == true){
//                // Checking if this network actually has internet
//                CoroutineScope(Dispatchers.IO).launch {
//                    val hasInternet = DoesNetworkHave
//                }
//            }
//        }
    }
}