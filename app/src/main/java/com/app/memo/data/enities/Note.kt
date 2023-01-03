package com.app.memo.data.enities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "data") val data: String?,
    @ColumnInfo(name = "tags") val tags: List<Tag>?
)