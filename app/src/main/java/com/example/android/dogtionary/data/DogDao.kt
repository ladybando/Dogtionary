package com.example.android.dogtionary.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao{
    @Query("SELECT * FROM images WHERE image = :imageUrl")
    suspend fun getImage(imageUrl: String): Dog

    @Query("SELECT * FROM images ORDER BY image DESC LIMIT 1")
    fun getAllImages(): Flow<List<Dog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(dog: Dog)

    @Delete
    suspend fun deleteImage(dog: Dog)
}