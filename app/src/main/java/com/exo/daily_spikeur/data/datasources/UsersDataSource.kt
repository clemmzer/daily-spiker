package com.exo.daily_spikeur.data.datasources

import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User

// Interface for the remote data source.
interface UsersRemoteDataSource {
    suspend fun getUser(): User
}

// Interface for the local data source.
interface UsersLocalDataSource {
    suspend fun getUserLocal(): User
    suspend fun getImageById(imageId: Int): Int?
    suspend fun getImagesIdByRarity(rarity: RarityEnum): IntArray
    suspend fun openChest(poopers: IntArray): Int
}