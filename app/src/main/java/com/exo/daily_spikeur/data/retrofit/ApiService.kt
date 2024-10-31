package com.exo.daily_spikeur.data.retrofit

import com.exo.daily_spikeur.data.models.User
import retrofit2.http.GET

interface ApiService {
    @GET("user")
    suspend fun getUser(): User
}

data class CreateUserRequest(
    val name: String,
)