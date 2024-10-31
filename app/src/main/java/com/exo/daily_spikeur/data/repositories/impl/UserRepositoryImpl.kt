package com.exo.daily_spikeur.data.repositories.impl;

import com.exo.daily_spikeur.data.datasources.UsersLocalDataSource
import com.exo.daily_spikeur.data.datasources.UsersRemoteDataSource
import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User
import com.exo.daily_spikeur.data.repositories.UserRepository

// This class is responsible for fetching data.
class UserRepositoryImpl(
    private val remoteDataSource: UsersRemoteDataSource,
    private val localDataSource: UsersLocalDataSource
) : UserRepository {
    override suspend fun getUser(): User {
        return try {
            // Fetch data from the remote data source.
            val response = remoteDataSource.getUser()
            response
        } catch (e: Exception) {
            // Fetch data from the local data source if an error occurs.
            localDataSource.getUserLocal()
        }
    }

    override suspend fun getImageById(imageId: Int): Int? {
        return localDataSource.getImageById(imageId)
    }

    override suspend fun getImagesIdByRarity(rarity: RarityEnum): IntArray {
        return localDataSource.getImagesIdByRarity(rarity)
    }

    override suspend fun openChest(poopers: IntArray): Int {
        return localDataSource.openChest(poopers)
    }
}
