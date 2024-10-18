package com.exo.daily_spikeur.data.datasources

import com.exo.daily_spikeur.data.models.User

// Interface for the remote data source.
interface UsersRemoteDataSource {
    suspend fun getUser(): User
}

// Interface for the local data source.
interface UsersLocalDataSource {
    suspend fun getUserLocal(): User
}