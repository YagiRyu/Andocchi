package com.github.ryu.andocchi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Github(
    val items: List<Item>
)

@JsonClass(generateAdapter = true)
data class Item(
    val full_name: String,
    val owner: Owner,
    val description: String,
    val url: String,
    val stargazers_count: Int
)

@JsonClass(generateAdapter = true)
data class Owner(
    val avatar_url: String,
    val url: String
)
