package com.urbn.android.flickster.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
       fun getAllCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(characters: List<CharacterEntity>)

   @Query("UPDATE character SET isFavorite = :isFavorite WHERE id = :id")
   suspend fun updateIsFavorite(id: Long, isFavorite: Boolean)

}