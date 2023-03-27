package com.urbn.android.flickster.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long =0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "details")
    val details: String,
    @ColumnInfo(name = "image_url")
    @Nullable
    val imageUrl: String? = null,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false
)


