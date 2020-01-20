package com.harsh.housingsocienty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImagesItem(
    @ColumnInfo(name = "image_text") val image_text: String = "",
    @ColumnInfo(name = "image_url") val image_url: String = "",
    @PrimaryKey @ColumnInfo(name = "id") val image_id: Int = 0
)