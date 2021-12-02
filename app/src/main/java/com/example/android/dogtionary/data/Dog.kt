package com.example.android.dogtionary.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Dog (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "image")
    val imageUrl: String
)