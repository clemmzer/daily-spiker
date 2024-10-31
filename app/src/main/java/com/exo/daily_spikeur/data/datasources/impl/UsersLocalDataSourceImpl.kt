package com.exo.daily_spikeur.data.datasources.impl

import com.exo.daily_spikeur.data.datasources.UsersLocalDataSource
import com.exo.daily_spikeur.data.models.RarityEnum
import com.exo.daily_spikeur.data.models.User
import com.exo.daily_spikeur.data.models.pooperMap
import kotlin.random.Random

class UsersLocalDataSourceImpl : UsersLocalDataSource {
    override suspend fun getUserLocal(): User = User("User", "local 1", "#LOCALID", 100, 1, 5, intArrayOf(1,2,3));
    override suspend fun getImageById(imageId: Int): Int? = pooperMap[imageId];

    override suspend fun getImagesIdByRarity(rarity: RarityEnum): IntArray {
        val poopers: IntArray = when (rarity) {
            RarityEnum.BASE -> intArrayOf(1, 2 ,3)
            RarityEnum.COMMON -> intArrayOf(4, 5, 6, 7, 8, 9, 10)
            RarityEnum.RARE -> intArrayOf(11, 12, 13, 14, 15, 16, 17, 18)
            RarityEnum.MYTHIC -> intArrayOf(19, 20, 21, 22, 23, 24, 25, 26, 27)
            RarityEnum.LEGENDARY -> intArrayOf(28, 29, 30, 31, 32, 33)
            RarityEnum.HONOR -> intArrayOf(34, 35, 36)
        }
        return poopers
    }

    override suspend fun openChest(poopers: IntArray): Int {
        val legendaryImages = getImagesIdByRarity(RarityEnum.LEGENDARY).filterNot { it in poopers }
        val mythicImages = getImagesIdByRarity(RarityEnum.MYTHIC).filterNot { it in poopers }
        val rareImages = getImagesIdByRarity(RarityEnum.RARE).filterNot { it in poopers }
        val commonImages = getImagesIdByRarity(RarityEnum.COMMON).filterNot { it in poopers }

        val availableImagesWithProbabilities = mutableListOf<Pair<List<Int>, Double>>()

        // Applique les probabilités par défaut
        if (legendaryImages.isNotEmpty()) availableImagesWithProbabilities.add(legendaryImages to 0.005)
        if (mythicImages.isNotEmpty()) availableImagesWithProbabilities.add(mythicImages to 0.055)
        if (rareImages.isNotEmpty()) availableImagesWithProbabilities.add(rareImages to 0.25)
        if (commonImages.isNotEmpty()) availableImagesWithProbabilities.add(commonImages to 0.69)


        if (availableImagesWithProbabilities.isEmpty()) {
            return -1 // Une gestion des erreurs avec un enum serait plus pertinent
        }

        // Pondère les probabilités en fonction du nombre de skins restant par type de rareté
        val totalProbability = availableImagesWithProbabilities.sumOf { it.second }
        val normalizedImagesWithProbabilities = availableImagesWithProbabilities.map { (images, probability) ->
            images to (probability / totalProbability)
        }

        val randomValue = Random.nextDouble()
        var accumulatedProbability = 0.0

        // On tire au sort un skin selon le nombre aléatoire tiré et les probabilités d'apparition
        for ((images, probability) in normalizedImagesWithProbabilities) {
            accumulatedProbability += probability
            if (randomValue <= accumulatedProbability) {
                return images.random()
            }
        }

        return -2 // Une gestion des erreurs avec un enum serait plus pertinent
    }
}