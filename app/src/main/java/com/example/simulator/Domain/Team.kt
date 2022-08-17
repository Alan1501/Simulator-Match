package com.example.simulator.Domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    @SerializedName("nome")
    val name: String,
    @SerializedName("estrelas")
    val stars: Int,
    @SerializedName("imagem")
    val image: String,
    var score: Int?
) : Parcelable