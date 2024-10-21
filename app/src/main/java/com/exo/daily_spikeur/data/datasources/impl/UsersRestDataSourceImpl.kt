package com.exo.daily_spikeur.data.datasources.impl

import com.exo.daily_spikeur.data.retrofit.ApiService;
import com.exo.daily_spikeur.data.datasources.UsersRemoteDataSource
import com.exo.daily_spikeur.data.models.User

class UsersRestDataSourceImpl(private val apiService:ApiService) :
    UsersRemoteDataSource {

    override suspend fun getUser(): User {
        return apiService.getUser()
    }

}