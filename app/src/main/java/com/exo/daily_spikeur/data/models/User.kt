package com.exo.daily_spikeur.data.models

data class User(
    val firstname: String,
    val lastname: String,
    val fakeId: String,
    val points: Int,
    val photo: Int,
    val poop_count: Int,
    val poopers: IntArray
)