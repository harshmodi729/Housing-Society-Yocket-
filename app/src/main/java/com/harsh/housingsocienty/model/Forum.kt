package com.harsh.housingsocienty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forum")
data class Forum(
    @ColumnInfo(name = "total_topics") val total_topics: Int = 0,
    @ColumnInfo(name = "description") val description: String = "",
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = ""
)