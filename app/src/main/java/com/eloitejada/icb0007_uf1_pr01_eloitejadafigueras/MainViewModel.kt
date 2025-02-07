package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(private val repository: RocketRepository): ViewModel() {

    var isSplashCompleted = false
        private set



    private val _rocketList = MutableStateFlow<List<Rocket>>(emptyList())
    val rocketList: StateFlow<List<Rocket>> get() = _rocketList

    init {
        fetchRockets() // Fetch rockets when ViewModel is created
    }

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    fun startSplash(onSplashFinished: () -> Unit) {
        if (!isSplashCompleted) {
            viewModelScope.launch {
                delay(3000)
                isSplashCompleted = true
                onSplashFinished()
            }
        } else {
            onSplashFinished()
        }
    }

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    private var isFetched = false

    fun fetchRockets() {
        if (isFetched) return

        viewModelScope.launch {
            try {
                val rockets = repository.fetchRocketsFromApi()

                if (rockets.isNotEmpty()) {
                    _rocketList.value = rockets
                    isFetched = true
                } else {
                    Log.w("MainViewModel", "No rockets fetched from API")
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching rockets: ${e.message}")
            }
        }
    }
    fun loadRockets() {
        viewModelScope.launch {
            _rocketList.value = repository.fetchRocketsFromApi()
        }
    }



    object RetrofitInstance {
        private const val BASE_URL = "https://api.spacexdata.com/v4/"

        val api: RocketAPIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RocketAPIService::class.java)
        }
    }
}
