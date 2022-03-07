package com.github.ryu.andocchi.model

data class AndroidDeveloperRoadmap(
    val paths: Path?
)

data class Path(
    val id: Int?,
    val title: String?,
    val sections: Section?
)

data class Section(
    val id: Int?,
    val title: String?,
    val isJetpack: Boolean?,
    val nodes: Node?
)

data class Node(
    val id: Int,
    val title: String?,
    val isJetpack: Boolean?,
    val priority: Int?,
    val childNodes: ChildNode?
)

data class ChildNode(
    val id: Int?,
    val title: String?,
    val isJetpack: Boolean?,
    val priority: Int?
)