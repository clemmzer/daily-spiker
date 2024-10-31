package com.exo.daily_spikeur.data.repositories;

import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User

public interface UserRepository {
    suspend fun getUser(): User
    suspend fun getImageById(imageId: Int): Int?
    suspend fun getImagesIdByRarity(rarity: RarityEnum): IntArray
    suspend fun openChest(poopers: IntArray): Int
}
