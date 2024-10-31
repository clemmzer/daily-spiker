package com.exo.daily_spikeur.data.repositories.impl;

import com.exo.daily_spikeur.data.datasources.UsersLocalDataSource
import com.exo.daily_spikeur.data.datasources.UsersRemoteDataSource
import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User
import com.exo.daily_spikeur.data.repositories.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UsersRemoteDataSource,
    private val localDataSource: UsersLocalDataSource
) : UserRepository {
    override suspend fun getUser(): User {
        return try {
            val response = remoteDataSource.getUser()
            response
        } catch (e: Exception) {
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
