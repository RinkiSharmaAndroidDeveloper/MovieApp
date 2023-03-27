package com.urbn.android.flickster.data.remote

import androidx.lifecycle.LiveData
import com.urbn.android.flickster.db.CharacterDao
import com.urbn.android.flickster.db.CharacterEntity

class CharacterRoomRepository(private val characterDao: CharacterDao) {

//      fun getAllCharacters(): List<CharacterEntity>{
//      return characterDao.getAllCharacters()
//
//    }

     fun insertAll(characters: List<CharacterEntity>) {
        characterDao.insertAll(characters)
    }

//    suspend fun updateIsFavorite(id: Long, isFavorite: Boolean) {
//        characterDao.updateIsFavorite(id, isFavorite)
//    }
}