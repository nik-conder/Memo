package com.app.memo.data.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "color") val color: Long?
)
