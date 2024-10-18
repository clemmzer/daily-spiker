package com.exo.daily_spikeur.data.repositories;

import com.exo.daily_spikeur.data.models.User

public interface UserRepository {
    suspend fun getUser(): User
}
