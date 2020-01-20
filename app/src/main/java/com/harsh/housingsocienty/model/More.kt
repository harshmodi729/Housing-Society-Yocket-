package com.harsh.housingsocienty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "more")
data class More(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "last_notification_text") val last_notification_text: String = "",
    @ColumnInfo(name = "notification_count") val notification_count: Int = 0,
    @ColumnInfo(name = "neighbours") val neighbours: Int = 0,
    @ColumnInfo(name = "staff") val staff: Int = 0,
    @ColumnInfo(name = "contacts") val contacts: Int = 0
) {
    @Ignore
    val images: ArrayList<ImagesItem> = ArrayList()
}