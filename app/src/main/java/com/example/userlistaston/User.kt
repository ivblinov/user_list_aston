package com.example.userlistaston

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    var name: String,
    var lastName: String,
    var phoneNumber: Long,
    var photo: Int
) : Parcelable
