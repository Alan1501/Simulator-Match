package com.example.simulator.Domain

data class Match(
    val description: String,
    val place: Place,
    val homeTeam: Team,
    val awayTeam: Team,
)
