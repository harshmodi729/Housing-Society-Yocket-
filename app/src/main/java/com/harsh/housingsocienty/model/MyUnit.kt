package com.harsh.housingsocienty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_unit")
data class MyUnit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "next_due") val next_due: String = "",
    @ColumnInfo(name = "closed_complaint") val closed_complaint: Int = 0,
    @ColumnInfo(name = "leases_detail") val leases_detail: Int = 0,
    @ColumnInfo(name = "due") val due: Double = 0.0,
    @ColumnInfo(name = "total_members") val total_members: Int = 0,
    @ColumnInfo(name = "open_complaint") val open_complaint: Int = 0
)