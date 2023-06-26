package com.lazy.producttask.viewmodel

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazy.producttask.api.Repository
import com.lazy.producttask.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: Repository,
    private val connectivityManager: ConnectivityManager,
) : ViewModel() {

    private val productsResponse = MutableLiveData<Product>()
    val products: LiveData<Product> get() = productsResponse

    fun getAllProducts() = viewModelScope.launch {
        if (isConnected()) {
            val response = repository.getAllProducts()
            if (response.isSuccessful) {
                productsResponse.postValue(response.body())
            }
        }
    }

    private fun isConnected(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}