package com.github.ryu.andocchi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "level")
    val level: Int,

    @ColumnInfo(name = "skill_list")
    val skillList: MutableList<String>,

    @ColumnInfo(name = "memo")
    val memo: String?,
//
//    @ColumnInfo(name = "section")
//    val sections: List<String>,
//
//    @ColumnInfo(name = "node")
//    val nodes: List<String>,
//
//    @ColumnInfo(name = "child_node")
//    val child_nodes: List<String>
)
