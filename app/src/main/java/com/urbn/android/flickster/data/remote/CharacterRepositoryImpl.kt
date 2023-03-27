package com.urbn.android.flickster.data.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.urbn.android.flickster.db.CharacterEntity
import com.urbn.android.flickster.db.DatabaseService
import com.urbn.android.flickster.presentation.util.ConnectionUtilData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: CharacterRepoApi,
    private val databaseService: DatabaseService,
    private val applicationContext: Context
) {
    private var _characters = MutableLiveData<List<CharacterEntity>>()
    val characters: LiveData<List<CharacterEntity>>
        get() = _characters


    suspend fun fetchMoviesList() {
        //if Network is present
        if (ConnectionUtilData.isNetworkAvailable(context = applicationContext)) {
            val response = api.getCharacterList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val characterEntities = response.body()?.relatedTopics?.map { it ->
                        CharacterEntity(
                            name = getFormattedName(it.text) ?: "",
                            details = getDetails(it.text) ?: "",
                            imageUrl = getImageUrl(it.icon.url),
                            isFavorite = false
                        )
                    }
                    withContext(Dispatchers.IO) {
                        characterEntities.let {
                            if (it != null) {
                                databaseService?.characterDao()?.insertAll(it)
                            }
                        }
                    }
                    _characters.postValue(characterEntities)
                } else {
                    Log.d("Error: ", "${response.message()}")
                }
            }
        }
        // get data from the database
        else {
            _characters.postValue(databaseService?.characterDao()?.getAllCharacters())
        }
    }

    suspend fun updateIsFavorite(id: Long, isFavorite: Boolean) {
        databaseService?.characterDao()?.updateIsFavorite(id, isFavorite)
    }

}
