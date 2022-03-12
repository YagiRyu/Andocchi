package com.github.ryu.andocchi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Path(
    val id: Int?,
    val title: String?,
    val sections: List<Section>?
)

@JsonClass(generateAdapter = true)
data class Section(
    val id: Int?,
    val title: String?,
    val isJetpack: Boolean?,
    val nodes: List<Node>?
)

@JsonClass(generateAdapter = true)
data class Node(
    val id: Int,
    val title: String?,
    val isJetpack: Boolean?,
    val priority: Int?,
    val childNodes: List<ChildNode>?
)

@JsonClass(generateAdapter = true)
data class ChildNode(
    val id: Int?,
    val title: String?,
    val isJetpack: Boolean?,
    val priority: Int?
)

data class Item(
    val title: String,
    val nodes: List<Node>
)
