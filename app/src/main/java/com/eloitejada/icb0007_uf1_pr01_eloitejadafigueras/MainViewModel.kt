package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var isSplashCompleted = false
        private set

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
}