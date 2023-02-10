package com.app.memo.data.enities

import androidx.room.*

class ConvertTags {
    @TypeConverter
    fun fromTimestamp(tagsList: List<Tag>): String {
        return tagsList.toString()
    }

    @TypeConverter
    fun dateToTimestamp(tag: Tag): String {
        return tag.toString()
    }
}

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "text") val text: String? = null,
   //@ColumnInfo(name = "data") val data: String? = null,
   //@ColumnInfo(name = "tags") val tags: String? = null,
   //@ColumnInfo(name = "color") val color: Long = 0xFFFFC300
)