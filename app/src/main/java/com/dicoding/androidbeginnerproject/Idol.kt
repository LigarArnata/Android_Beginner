package com.dicoding.androidbeginnerproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Idol(
    val name: String,
    val description: String,
    val photo: Int
):Parcelable
