package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import retrofit2.Response
import retrofit2.http.GET

interface RocketAPIService {
    @GET("rockets")

    suspend fun getRockets(): Response<List<Rocket>>

}