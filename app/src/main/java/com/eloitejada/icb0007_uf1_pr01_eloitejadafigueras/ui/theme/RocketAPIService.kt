package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras.ui.theme

import retrofit2.Call
import retrofit2.http.GET

interface RocketAPIService {
    @GET("rockets")

    suspend fun getRockets(): Call<List<Rocket>>

}