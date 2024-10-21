package com.exo.daily_spikeur.data.datasources.impl

import com.exo.daily_spikeur.R
import com.exo.daily_spikeur.data.datasources.UsersLocalDataSource
import com.exo.daily_spikeur.data.models.User

class UsersLocalDataSourceImpl : UsersLocalDataSource {
            override suspend fun getUserLocal(): User = User("User", "local 1", "#LOCALID", 100, R.drawable.honor_gambier, 5, intArrayOf(1,2,3));
}