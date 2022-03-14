package com.github.ryu.andocchi.utils

fun Int.createId() = when (this) {
    0 -> 0
    1 -> 1
    2 -> 2
    3 -> 3
    4 -> 4
    5, 6, 7, 8, 9 -> 5
    10, 11 -> 6
    12 -> 7
    13 -> 8
    14 -> 9
    15 -> 10
    16, 17 -> 11
    18 -> 12
    19 -> 13
    20 -> 14
    else -> 15
}

fun MutableList<String>.createMutableList() =
    this[0].split(",").filter { it != "" }.toMutableList()
