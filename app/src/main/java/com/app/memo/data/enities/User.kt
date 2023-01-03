package com.app.memo.data.enities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String
)