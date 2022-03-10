package com.github.ryu.andocchi.utils

import androidx.room.TypeConverter

// Roomで、List<String>が保存できないので、保存できるようにStringにして、`,`で区切るようにした。
class RoomTypeConverters {
    companion object {

        @TypeConverter
        @JvmStatic
        fun toCsvString(list: MutableList<Int>): String = list.joinToString(",")

        @TypeConverter
        @JvmStatic
        fun toList(skillList: String): MutableList<Int> = skillList.split(",").map { it.toInt() }.toMutableList()

        @JvmStatic
        @TypeConverter
        fun commaStringToList(commaString: String): List<String> =
            commaString.split(",").map { it.trim() }

        @JvmStatic
        @TypeConverter
        fun listToComma(list: List<String>) = list.toTypedArray().joinToString(",")
    }
}
