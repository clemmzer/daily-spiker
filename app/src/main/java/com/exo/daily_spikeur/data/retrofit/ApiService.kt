package com.exo.daily_spikeur.data.retrofit

import com.exo.daily_spikeur.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //GET https://api.github.com/users/1
    @GET("user")
    suspend fun getUser(): User
}

data class CreateUserRequest(
    val name: String,
)