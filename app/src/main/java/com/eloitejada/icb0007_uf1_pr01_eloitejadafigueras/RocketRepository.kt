package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.util.Log

class RocketRepository(private val rocketDao: RocketDAO) {

    suspend fun fetchRocketsFromApi(): List<Rocket> {
        return try {

            val rockets = MainViewModel.RetrofitInstance.api.getRockets()

            if (rockets.isNotEmpty()) {
                rocketDao.insert(rockets)
            }
            rockets
        } catch (e: Exception) {
            Log.e("RocketRepository", "Error: ${e.message}")
            rocketDao.getAll()
        }
    }
}

