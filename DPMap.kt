package com.example.dpmap.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DPMap(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)